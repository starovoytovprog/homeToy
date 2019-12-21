package ru.starovoytov.home.toy.common.libs.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.starovoytov.home.toy.common.libs.exceptions.HttpClientException;
import ru.starovoytov.home.toy.common.libs.log.CommonLogMessageBuilder;
import ru.starovoytov.home.toy.common.libs.proto.BaseResponseBody;
import ru.starovoytov.home.toy.common.libs.proto.Message;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import static ru.starovoytov.home.toy.common.libs.ConstantsHelper.HTTP_TIMEOUT;
import static ru.starovoytov.home.toy.common.libs.log.MarkersHelper.HTTP_CLIENT;
import static ru.starovoytov.home.toy.common.libs.log.MarkersHelper.HTTP_CLIENT_REQUEST;
import static ru.starovoytov.home.toy.common.libs.log.MarkersHelper.HTTP_CLIENT_RESPONSE;

/**
 * Класс для обращения к http-сервисам
 *
 * @author starovoytov
 * @since 2019.12.19
 */
public final class HttpClientUtility {
	private static final String GET = "GET";
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final Logger LOGGER = LogManager.getLogger(HttpClientUtility.class);

	/**
	 * private-конструктор
	 */
	private HttpClientUtility() {
	}

	/**
	 * Отправляет пустой get-запрос на адрес и получает результат
	 *
	 * @param address Адрес запрашиваемого документа
	 * @param uid     уникальный идентификатор операции
	 * @return Контент страницы ответа
	 * @throws IOException         Ошибки запроса к серверу
	 * @throws HttpClientException Ошибки запроса к серверу
	 */
	public static HttpURLConnection sendEmptyGetRequest(final String address,
		final String uid) throws IOException, HttpClientException {
		return sendRequestWithParameters(address, null, null, GET, uid);
	}

	/**
	 * Отправляет get-запрос с json на адрес и получает результат
	 *
	 * @param address   Адрес запрашиваемого документа
	 * @param json      json запроса
	 * @param reference ссылка на тип сообщения
	 * @param uid       ouid операции
	 * @param <B>       класс тела ответа.
	 * @return Контент страницы ответа
	 * @throws IOException         Ошибки запроса к серверу
	 * @throws HttpClientException Ошибки запроса к серверу
	 */
	@SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
	public static <B extends BaseResponseBody> Message<B> sendJsonGetRequest(final String address,
		final JsonNode json, final TypeReference<Message<B>> reference,
		final String uid) throws IOException, HttpClientException {

		LOGGER.info(HTTP_CLIENT_REQUEST, () -> CommonLogMessageBuilder.create()
			.addUri(address)
			.addMsg("Send request")
			.addUid(uid)
			.addJsonMessage(json)
			.build());

		long timeMarker = System.currentTimeMillis();

		try (InputStream iStream = sendRequestWithParameters(address, json, null, GET, uid).getInputStream()) {
			final Message<B> response = MAPPER.readValue(iStream, reference);

			timeMarker = System.currentTimeMillis() - timeMarker;
			final long finalTimeMarker = timeMarker;

			LOGGER.info(HTTP_CLIENT_RESPONSE, () -> CommonLogMessageBuilder.create()
				.addUri(address)
				.addUid(uid)
				.addMsg("Receive response")
				.addOperationTime(finalTimeMarker)
				.addJsonMessage(MAPPER.convertValue(response, JsonNode.class))
				.build());

			return response;
		}
	}

	/**
	 * Отправить запрос с параметрами
	 *
	 * @param address    адрес подключения
	 * @param parameters параметры
	 * @param param      свойства запроса
	 * @param method     метод
	 * @param uid        уникальный идентификатор операции
	 * @return подключение
	 * @throws HttpClientException ошибка подключения
	 * @throws IOException         ошибки ввода-вывода в подключение
	 */
	private static HttpURLConnection sendRequestWithParameters(final String address, final JsonNode parameters,
		final Map<String, String> param, final String method,
		final String uid) throws HttpClientException, IOException {
		final HttpURLConnection connection = getConnection(address, method, uid);

		if (param != null) {
			for (final Map.Entry<String, String> entry : param.entrySet()) {
				connection.setRequestProperty(entry.getKey(), entry.getValue());
			}
		}

		if (parameters != null) {
			connection.setDoOutput(true);
			try (OutputStream outputStream = connection.getOutputStream()) {
				outputStream.write(getParametersAsBytes(parameters));
				outputStream.flush();
			} catch (ConnectException ex) {
				LOGGER.fatal(HTTP_CLIENT, () -> CommonLogMessageBuilder.create()
					.addUri(address)
					.addMsg("Request failed")
					.addUid(uid)
					.build(), ex);
				throw new HttpClientException(ex.getMessage() + " (" + address + ")", ex);
			}
		}

		return connection;
	}

	/**
	 * Установка соединения
	 *
	 * @param address       Адрес документа
	 * @param requestMethod Метод запроса
	 * @param uid           уникальный идентификатор операции
	 * @return Коннектор
	 * @throws HttpClientException Ошибки подключения
	 */
	@SuppressWarnings({"PMD.LawOfDemeter"})
	private static HttpURLConnection getConnection(final String address, final String requestMethod,
		final String uid) throws HttpClientException {
		try {
			final URL url = new URL(address);
			final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(HTTP_TIMEOUT);
			connection.setReadTimeout(HTTP_TIMEOUT);
			connection.setRequestMethod(requestMethod);
			return connection;
		} catch (IOException ex) {
			LOGGER.fatal(HTTP_CLIENT, () -> CommonLogMessageBuilder.create()
				.addUri(address)
				.addMsg("HTTP connection failed")
				.addUid(uid)
				.build(), ex);
			throw new HttpClientException(ex.getMessage() + " (" + address + ")", ex);
		}
	}

	/**
	 * Преобразует параметры в строку
	 *
	 * @param parameters входные параметры
	 * @return строка параметров
	 * @throws IOException Ошибка записи объекта
	 */
	@SuppressWarnings({"PMD.OnlyOneReturn"})
	private static byte[] getParametersAsBytes(final JsonNode parameters) throws IOException {
		if (parameters == null) {
			return new byte[0];
		}

		return new ObjectMapper().writer().writeValueAsBytes(parameters);
	}
}
