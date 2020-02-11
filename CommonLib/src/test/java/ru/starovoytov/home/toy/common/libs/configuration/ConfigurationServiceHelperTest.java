package ru.starovoytov.home.toy.common.libs.configuration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.starovoytov.home.toy.test.utils.undertow.HttpHandlerDescriptor;
import ru.starovoytov.home.toy.test.utils.undertow.UndertowHttpService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
		descriptors.add(new HttpHandlerDescriptor("conf", new ConfiguratorHandler()));
		service = new UndertowHttpService(SERVICE_PORT, "localhost", descriptors);
		service.start();
	}

	/**
	 * Тест получения значения из сервиса по несуществующему адресу
	 */
	@Test
	public void testGetFromBadAddress() {
		final String value = getValueFromService("key", "bad address");
		assertNull(value, "Not null value from bad address");
	}

	/**
	 * Тест получения значения из сервиса
	 */
	@Test
	public void testGetFromConfigurator() {
		final String value = getValueFromService("key", "http://localhost:" + SERVICE_PORT + "/conf");
		assertEquals("[key] value", value, "Bad value");
	}

	/**
	 * Тест получения значения из сервиса 2
	 */
	@Test
	public void testGetFromConfigurator2() {
		final String value = getValueFromService("key2", "http://localhost:" + SERVICE_PORT + "/conf");
		assertEquals("[key2] value", value, "Bad value");
	}

	/**
	 * Остановка сервиса
	 */
	@AfterAll
	public static void stopService() {
		service.stop();
	}
}