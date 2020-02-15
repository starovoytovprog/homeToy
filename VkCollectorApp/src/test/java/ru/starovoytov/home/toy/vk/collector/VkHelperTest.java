package ru.starovoytov.home.toy.vk.collector;

import org.junit.jupiter.api.Test;
import ru.starovoytov.home.toy.vk.exceptions.VkException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.starovoytov.home.toy.vk.collector.VkHelper.getLastPostsUrl;

/**
 * Тест помошника для работы с ВК
 *
 * @author starovoytov
 * @since 2020.02.15
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
class VkHelperTest {
	/**
	 * Тест получения адресов постов со стены ВК
	 *
	 * @throws VkException ошибка получения списка постов
	 */
	@Test
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public void testReceivePosts() throws VkException {
		final TestConfigurator configurator = new TestConfigurator();
		assertTrue(getLastPostsUrl(configurator.getTestOwnerId(), 0).contains(configurator.getTestAddress()), "Message not found");
	}

	/**
	 * Тест ошибки
	 */
	@Test
	@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.LawOfDemeter"})
	public void exceptionTest() {
		final Exception exception = assertThrows(VkException.class, () -> getLastPostsUrl(Integer.MAX_VALUE, 0));
		assertEquals("Ошибка получения списка постов", exception.getMessage(), "Bad error message");
	}
}
