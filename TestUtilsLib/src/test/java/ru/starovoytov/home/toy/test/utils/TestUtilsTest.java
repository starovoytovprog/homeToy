package ru.starovoytov.home.toy.test.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.starovoytov.home.toy.test.utils.TestUtils.getFreePort;
import static ru.starovoytov.home.toy.test.utils.TestUtils.setEnv;

/**
 * Тест утилит для тестов {@link TestUtils}
 *
 * @author starovoytov
 * @since 2019.12.20
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
class TestUtilsTest {
	private static final String TEST = "test";

	/**
	 * Тест поиска свободного порта
	 */
	@Test
	public void freePortTest() {
		final int generatedPort = getFreePort();
		assertTrue(generatedPort >= 124 && generatedPort <= 49_151, "Generated port id bad");
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
