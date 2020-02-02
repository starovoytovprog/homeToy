package ru.starovoytov.home.toy.web.library;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.starovoytov.home.toy.common.libs.configuration.AbstractConfigurator;
import ru.starovoytov.home.toy.common.libs.exceptions.HttpClientException;

import java.io.IOException;
import java.util.ArrayList;

import static ru.starovoytov.home.toy.test.utils.TestUtils.getFreePort;

/**
 * Тест хэлпера для генерации идентификаторов операции {@link UndertowHttpService}
 *
 * @author starovoytov
 * @since 2019.12.19
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
		final AbstractConfigurator configurator = new AbstractConfigurator() {
			@Override
			protected void fillDefaultParameters() {
			}
		};

		service = new UndertowHttpService(SERVICE_PORT, "localhost", new ArrayList<>(), configurator);
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
		//final HttpURLConnection connection = HttpClientUtility.sendEmptyGetRequest("http://localhost:" + SERVICE_PORT + "/hello", "test uid");
		//assertEquals(200, connection.getResponseCode(), "Hello don't started");
	}

	/**
	 * Остановка сервиса
	 */
	@AfterAll
	public static void stopService() {
		service.stop();
	}
}
