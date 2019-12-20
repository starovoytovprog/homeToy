package ru.starovoytov.home.toy.test.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.starovoytov.home.toy.test.utils.TestUtils.getFreePort;

/**
 * Тест утилит для тестов {@link TestUtils}
 *
 * @author starovoytov
 * @since 2019.12.20
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
class TestUtilsTest {
	/**
	 * Тест поиска свободного порта
	 */
	@Test
	public void freePortTest() {
		final int generatedPort = getFreePort();
		assertTrue(generatedPort >= 124 && generatedPort <= 49_151, "Generated port id bad");
	}
}