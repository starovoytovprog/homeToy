package ru.starovoytov.home.toy.test.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.starovoytov.home.toy.test.utils.TestUtils.getFreePort;
import static ru.starovoytov.home.toy.test.utils.TestUtils.setEnv;

/**
 * Тест утилит для тестов {@link TestUtils}
 *
 * @author starovoytov
 * @since 2019.12.20
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor", "PMD.TooManyStaticImports"})
class TestUtilsTest {
	private static final String TEST = "test";
	private static final String BAD_PORT = "Generated port id bad";

	/**
	 * Тест поиска свободного порта
	 */
	@Test
	public void freePortTest1() {
		final int generatedPort = getFreePort();
		assertTrue(generatedPort >= 124, BAD_PORT);
	}

	/**
	 * Тест поиска свободного порта
	 */
	@Test
	public void freePortTest2() {
		final int generatedPort = getFreePort();
		assertTrue(generatedPort <= 49_151, BAD_PORT);
	}

	/**
	 * Тест поиска свободного порта
	 *
	 * @throws IOException ошибка ввода-вывода
	 */
	@Test
	public void freePortTest3() throws IOException {
		final int generatedPort1 = getFreePort(5_000);
		try (ServerSocket sock = new ServerSocket(generatedPort1)) {
			final int generatedPort2 = getFreePort(5_000);
			assertNotEquals(generatedPort1, generatedPort2, BAD_PORT);
		}
	}

	/**
	 * Тест обновления значения окружения
	 */
	@Test
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public void setEnvTest() {
		setEnv(TEST, TEST);
		assertEquals(TEST, System.getenv().get(TEST), "Not correct environment value");
	}
}
