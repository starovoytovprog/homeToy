package ru.starovoytov.home.toy.common.libs.configuration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.starovoytov.home.toy.test.utils.undertow.HttpHandlerDescriptor;
import ru.starovoytov.home.toy.test.utils.undertow.UndertowHttpService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static ru.starovoytov.home.toy.common.libs.configuration.ConfigurationServiceHelper.getValueFromService;
import static ru.starovoytov.home.toy.test.utils.TestUtils.getFreePort;

/**
 * Тест помошника для работы с сервисом конфигурации {@link ConfigurationServiceHelper}
 *
 * @author starovoytov
 * @since 2020.02.11
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
class ConfigurationServiceHelperTest {
	private static UndertowHttpService service;
	private static final int SERVICE_PORT = getFreePort();

	/**
	 * Конфигурация и запуск сервиса
	 */
	@BeforeAll
	public static void startService() {
		final List<HttpHandlerDescriptor> descriptors = new ArrayList<>();
		service = new UndertowHttpService(SERVICE_PORT, "localhost", descriptors);
		service.start();
	}

	@Test
	public void testGetFromBadAddress() {
		final String value = getValueFromService("key", "bad address");
		assertNull(value, "Not null value from bad address");
	}

	/**
	 * Остановка сервиса
	 */
	@AfterAll
	public static void stopService() {
		service.stop();
	}
}