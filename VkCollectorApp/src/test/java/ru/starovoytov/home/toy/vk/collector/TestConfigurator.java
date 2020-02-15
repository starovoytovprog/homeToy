package ru.starovoytov.home.toy.vk.collector;

import ru.starovoytov.home.toy.common.libs.configuration.AbstractConfigurator;

/**
 * Тестовый конфигуратор
 *
 * @author starovoytov
 * @since 2020.02.15
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
public class TestConfigurator extends AbstractConfigurator {
	@Override
	protected void fillDefaultParameters() {
	}

	/**
	 * Получить id стены, по которой идёт тест
	 *
	 * @return id стены, по которой идёт тест
	 */
	public int getTestOwnerId() {
		return getIntParameter("VK_TEST_OWNER_ID");
	}

	/**
	 * Получить тестовый адрес поста
	 *
	 * @return тестовый адрес поста
	 */
	public String getTestAddress() {
		return getStringParameter("VK_TEST_POST_ADDRESS");
	}
}
