package ru.starovoytov.home.toy.web.library;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.starovoytov.home.toy.common.libs.exceptions.HttpClientException;
import ru.starovoytov.home.toy.common.libs.http.HttpClientUtility;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест хэлпера для генерации идентификаторов операции {@link UndertowHttpService}
 *
 * @author starovoytov
 * @since 2019.12.19
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
class UndertowHttpServiceTest {
	private static UndertowHttpService service;

	/**
	 * Конфигурация и запуск сервиса
	 */
	@BeforeAll
	public static void startService() {
		service = new UndertowHttpService(1111, "localhost", new ArrayList<>());
		service.start();
	}

	/**
	 * Проверка кода ответа сервиса
	 *
	 * @throws IOException         ошибка обращения к сервису
	 * @throws HttpClientException ошибка обращения к сервису
	 */
	@Test
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public void helloTest() throws IOException, HttpClientException {
		final HttpURLConnection connection = HttpClientUtility.sendEmptyGetRequest("http://localhost:1111/hello", "test uid");
		assertEquals(200, connection.getResponseCode(), "Hello don't started");
	}

	/**
	 * Остановка сервиса
	 */
	@AfterAll
	public static void stopService() {
		service.stop();
	}
}
