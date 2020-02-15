package ru.starovoytov.home.toy.vk.collector;

import ru.starovoytov.home.toy.common.libs.configuration.AbstractConfigurator;
import ru.starovoytov.home.toy.common.libs.rabbit.RabbitMqInstanceParameters;

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

	public RabbitMqInstanceParameters getRabbitInstanceParameters() {
		return new RabbitMqInstanceParameters(getStringParameter("TEST_RABBIT_MQ_HOST"), getStringParameter("TEST_RABBIT_MQ_LOGIN"), getStringParameter("TEST_RABBIT_MQ_PASSWORD"));
	}

	public RabbitMqInstanceParameters getBadRabbitInstanceParameters() {
		return new RabbitMqInstanceParameters("bad host", getStringParameter("TEST_RABBIT_MQ_LOGIN"), getStringParameter("TEST_RABBIT_MQ_PASSWORD"));
	}
}
