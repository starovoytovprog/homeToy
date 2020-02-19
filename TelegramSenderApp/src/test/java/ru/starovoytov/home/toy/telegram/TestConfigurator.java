package ru.starovoytov.home.toy.telegram;

import ru.starovoytov.home.toy.common.libs.configuration.AbstractConfigurator;

/**
 * Конфигуратор для тестов
 *
 * @author starovoytov
 * @since 2020.02.19
 */
public final class TestConfigurator extends AbstractConfigurator {
	/**
	 * Экземпляр конфигуратора
	 */
	private static final transient TestConfigurator INSTANCE = new TestConfigurator();

	/**
	 * Конструктор по умолчанию
	 */
	private TestConfigurator() {
		super();
	}

	/**
	 * Получить экземпляр конфигуратора
	 *
	 * @return экземпляр конфигуратора
	 */
	public static TestConfigurator getInstance() {
		return INSTANCE;
	}

	@Override
	protected void fillDefaultParameters() {
	}

	/**
	 * Получить токен тестового бота
	 *
	 * @return токен тестового бота
	 */
	public String getTestBotToken() {
		return getStringParameter("TELEGRAM_TEST_BOT_TOKEN");
	}

	/**
	 * Получить имя тестового бота
	 *
	 * @return имя тестового бота
	 */
	public String getTestBotName() {
		return getStringParameter("TELEGRAM_TEST_BOT_NAME");
	}
}
