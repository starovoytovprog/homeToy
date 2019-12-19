package ru.starovoytov.home.toy.common.libs.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.starovoytov.home.toy.common.libs.exceptions.HttpClientException;
import ru.starovoytov.home.toy.common.libs.log.CommonLogMessageBuilder;
import ru.starovoytov.home.toy.common.libs.proto.AbstractResponseBody;
import ru.starovoytov.home.toy.common.libs.proto.Message;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import static ru.starovoytov.home.toy.common.libs.ConstantsHelper.HTTP_REQUEST_TIMEOUT;
import static ru.starovoytov.home.toy.common.libs.log.MarkersHelper.HTTP_CLIENT;
import static ru.starovoytov.home.toy.common.libs.log.MarkersHelper.HTTP_CLIENT_REQUEST;
import static ru.starovoytov.home.toy.common.libs.log.MarkersHelper.HTTP_CLIENT_RESPONSE;

/**
 * Класс для обращения к http-сервисам
 *
 * @author starovoytov
 * @since 2019.12.19
 */
public class HttpClient {
	private static final String REQUEST_METHOD_GET = "GET";
	private static final ObjectMapper MAPPER = new ObjectMapper();
	private static final Logger LOGGER = LogManager.getLogger(HttpClient.class);

	/**
	 * Отправляет пустой get-запрос на адрес и получает результат
	 *
	 * @param address Адрес запрашиваемого документа
	 * @param uid     уникальный идентификатор операции
	 * @return Контент страницы ответа
	 * @throws IOException         Ошибки запроса к серверу
	 * @throws HttpClientException Ошибки запроса к серверу
	 */
	public static HttpURLConnection sendEmptyGetRequest(String address,
		String uid) throws IOException, HttpClientException {
		return sendRequestWithParameters(address, null, null, REQUEST_METHOD_GET, uid);
	}

	/**
	 * Отправляет get-запрос с json на адрес и получает результат
	 *
	 * @param address   Адрес запрашиваемого документа
	 * @param json      json запроса
	 * @param reference ссылка на тип сообщения
	 * @param ouid      ouid операции
	 * @param <B>       класс тела ответа.
	 * @return Контент страницы ответа
	 * @throws IOException         Ошибки запроса к серверу
	 * @throws HttpClientException Ошибки запроса к серверу
	 */
	public static <B extends AbstractResponseBody> Message<B> sendJsonGetRequest(String address, JsonNode json,
		TypeReference<Message<B>> reference, String ouid) throws IOException, HttpClientException {
		long startTime = System.currentTimeMillis();

		LOGGER.info(HTTP_CLIENT_REQUEST, () -> CommonLogMessageBuilder.create()
			.addUri(address)
			.addMsg("Send request")
			.addUid(ouid)
			.addJsonMessage(json)
			.build());

		HttpURLConnection connection = sendRequestWithParameters(address, json, null, REQUEST_METHOD_GET, ouid);
		Message<B> response = MAPPER.readValue(connection.getInputStream(), reference);

		LOGGER.info(HTTP_CLIENT_RESPONSE, () -> CommonLogMessageBuilder.create()
			.addUri(address)
			.addUid(ouid)
			.addMsg("Receive response")
			.addOperationTime(System.currentTimeMillis() - startTime)
			.addJsonMessage(MAPPER.convertValue(response, JsonNode.class))
			.build());

		return response;
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
	private static HttpURLConnection sendRequestWithParameters(String address, JsonNode parameters,
		Map<String, String> param, String method, String uid) throws HttpClientException, IOException {
		HttpURLConnection connection = getConnection(address, method, uid);

		if (param != null) {
			for (Map.Entry<String, String> entry : param.entrySet()) {
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
	private static HttpURLConnection getConnection(String address, String requestMethod,
		String uid) throws HttpClientException {
		try {
			URL url = new URL(address);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setConnectTimeout(HTTP_REQUEST_TIMEOUT);
			connection.setReadTimeout(HTTP_REQUEST_TIMEOUT);
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
	private static byte[] getParametersAsBytes(JsonNode parameters) throws IOException {
		if (parameters == null) {
			return new byte[0];
		}
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectWriter objectWriter = objectMapper.writer();

		return objectWriter.writeValueAsBytes(parameters);
	}
}
