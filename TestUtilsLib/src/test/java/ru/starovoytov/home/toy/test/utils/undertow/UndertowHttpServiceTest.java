package ru.starovoytov.home.toy.test.utils.undertow;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.starovoytov.home.toy.test.utils.TestUtils.getFreePort;

/**
 * Тест сервиса undertow {@link UndertowHttpService}
 *
 * @author starovoytov
 * @since 2020.02.11
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
class UndertowHttpServiceTest {
	private static UndertowHttpService service;
	private static final int SERVICE_PORT = getFreePort();

	/**
	 * Конфигурация и запуск сервиса
	 */
	@BeforeAll
	public static void startService() {
		service = new UndertowHttpService(SERVICE_PORT, "localhost", new ArrayList<>());
		service.start();
	}

	/**
	 * Проверка кода ответа сервиса
	 *
	 * @throws IOException ошибка обращения к сервису
	 */
	@Test
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public void helloTest() throws IOException {
		final URL url = new URL("http://localhost:" + SERVICE_PORT + "/hello");
		final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
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
