package ru.starovoytov.home.toy.common.libs.http;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.starovoytov.home.toy.common.libs.exceptions.HttpClientException;
import ru.starovoytov.home.toy.test.utils.undertow.HttpHandlerDescriptor;
import ru.starovoytov.home.toy.test.utils.undertow.UndertowHttpService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.starovoytov.home.toy.common.libs.http.RequestHelper.httpEmptyGet;
import static ru.starovoytov.home.toy.common.libs.http.RequestHelper.sendEmptyGetRequest;
import static ru.starovoytov.home.toy.test.utils.TestUtils.getFreePort;

/**
 * Тест отправки http-запросов {@link RequestHelper}
 *
 * @author starovoytov
 * @since 2020.02.11
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor", "PMD.TooManyStaticImports"})
class RequestHelperTest {
	private static final String TEST_UID = "TEST_UID";

	private static UndertowHttpService service;
	private static final int SERVICE_PORT = getFreePort();

	/**
	 * Конфигурация и запуск сервиса
	 */
	@BeforeAll
	public static void startService() {
		final List<HttpHandlerDescriptor> descriptors = new ArrayList<>();
		descriptors.add(new HttpHandlerDescriptor("bad", new BadHandler()));
		service = new UndertowHttpService(SERVICE_PORT, "localhost", descriptors);
		service.start();
	}

	/**
	 * Тест отправки запроса по несуществующему адресу
	 */
	@Test
	@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.LawOfDemeter"})
	public void badAddressTest1() {
		final Exception exception = assertThrows(HttpClientException.class, () -> sendEmptyGetRequest("bad address", TEST_UID));
		assertEquals("HTTP connection failed", exception.getMessage(), "Bad exception message");
	}

	/**
	 * Тест отправки запроса по несуществующему адресу 2
	 */
	@Test
	@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.LawOfDemeter"})
	public void badAddressTest2() {
		final String response = httpEmptyGet("bad address", TEST_UID);
		assertEquals(null, response, "Not null message");
	}

	/**
	 * Тест отправки запроса по несуществующему адресу 2
	 */
	@Test
	@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.LawOfDemeter"})
	public void badHandler() {
		final String response = httpEmptyGet("http://localhost:" + SERVICE_PORT + "/bad", TEST_UID);
		assertEquals(null, response, "Not null message");
	}

	/**
	 * Тест отправки запроса по несуществующему адресу
	 */
	@Test
	public void helloTest() {
		final String response = httpEmptyGet("http://localhost:" + SERVICE_PORT + "/hello", TEST_UID);
		assertEquals("Hello!", response, "Bad hello message");
	}

	/**
	 * Остановка сервиса
	 */
	@AfterAll
	public static void stopService() {
		service.stop();
	}
}
