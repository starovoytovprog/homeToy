package ru.starovoytov.home.toy.common.libs.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.starovoytov.home.toy.common.libs.exceptions.HttpClientException;
import ru.starovoytov.home.toy.common.libs.log.CommonLogMessageBuilder;
import ru.starovoytov.home.toy.common.libs.log.MarkersHelper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static ru.starovoytov.home.toy.common.libs.ConstantsHelper.HTTP_TIMEOUT;

/**
 * Помошник для отправки http запросов
 *
 * @author starovoytov
 * @since 2020.02.10
 */
public final class RequestHelper {
	@SuppressWarnings({"PMD.LongVariable"})
	private static final String REQUEST_METHOD_GET = "GET";
	private static final Logger LOGGER = LogManager.getLogger(RequestHelper.class);

	/**
	 * Закрытый конструктор без параметров
	 */
	private RequestHelper() {
	}

	/**
	 * Получить ответ на пустой http-get запрос
	 *
	 * @param address Адрес запрашиваемого документа
	 * @param uid     Уникальный идентификатор операции
	 * @return Контент страницы ответа
	 */
	@SuppressWarnings({"PMD.LawOfDemeter", "PMD.DataflowAnomalyAnalysis"})
	public static String httpEmptyGet(final String address, final String uid) {
		String text = null;
		try (Scanner scanner = new Scanner(sendEmptyGetRequest(address, uid).getInputStream(), StandardCharsets.UTF_8.name())) {
			text = scanner.useDelimiter("\\A").next();
		} catch (HttpClientException | IOException ex) {
			LOGGER.error(MarkersHelper.SERVICE_REQUEST, () -> CommonLogMessageBuilder.create()
				.addUri(address)
				.addMsg("HTTP connection failed")
				.addUid(uid)
				.build(), ex);
		}

		return text;
	}

	/**
	 * Отправляет пустой get-запрос на адрес и получает результат
	 *
	 * @param address Адрес запрашиваемого документа
	 * @param uid     Уникальный идентификатор операции
	 * @return http-подключение
	 * @throws HttpClientException Ошибки запроса к серверу
	 */
	public static HttpURLConnection sendEmptyGetRequest(final String address,
		final String uid) throws HttpClientException {
		return getConnection(address, REQUEST_METHOD_GET, uid);
	}

	/**
	 * Установка соединения
	 *
	 * @param address       Адрес документа
	 * @param requestMethod Метод запроса
	 * @param uid           Уникальный идентификатор операции
	 * @return Коннектор
	 * @throws HttpClientException Ошибка обращения к серверу
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
			LOGGER.error(MarkersHelper.SERVICE_REQUEST, () -> CommonLogMessageBuilder.create()
				.addUri(address)
				.addMsg("HTTP connection failed")
				.addUid(uid)
				.build(), ex);
			throw new HttpClientException("HTTP connection failed", ex);
		}
	}
}
