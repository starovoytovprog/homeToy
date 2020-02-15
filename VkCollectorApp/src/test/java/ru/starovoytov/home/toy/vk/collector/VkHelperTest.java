package ru.starovoytov.home.toy.vk.collector;

import org.junit.jupiter.api.Test;
import ru.starovoytov.home.toy.common.libs.configuration.AbstractConfigurator;
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
		assertTrue(getLastPostsUrl(configurator.getTestOwnerId(), 0).contains(configurator.getTestAddress()), "Message not find");
	}

	/**
	 * Тест ошибки
	 */
	@Test
	@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.LawOfDemeter"})
	public void ExceptionTest() {
		final Exception exception = assertThrows(VkException.class, () -> getLastPostsUrl(Integer.MAX_VALUE, 0));
		assertEquals("Ошибка получения списка постов", exception.getMessage(), "Bad error message");
	}

	/**
	 * Тестовый конфигуратор
	 */
	private static class TestConfigurator extends AbstractConfigurator {
		@Override
		protected void fillDefaultParameters() {
		}

		/**
		 * Получить id стены, по которой идёт тест
		 *
		 * @return id стены, по которой идёт тест
		 */
		private int getTestOwnerId() {
			return getIntParameter("VK_TEST_OWNER_ID");
		}

		/**
		 * Получить тестовый адрес поста
		 *
		 * @return тестовый адрес поста
		 */
		private String getTestAddress() {
			return getStringParameter("VK_TEST_POST_ADDRESS");
		}
	}
}
