package ru.starovoytov.home.toy.telegram.configuration;

import ru.starovoytov.home.toy.common.libs.configuration.AbstractConfigurator;

import static ru.starovoytov.home.toy.common.libs.configuration.ConfigurationParametersHelper.HOST;
import static ru.starovoytov.home.toy.common.libs.configuration.ConfigurationParametersHelper.PORT;
import static ru.starovoytov.home.toy.telegram.configuration.ConfigurationParametersHelper.TELEGRAM_PROXY_HOST;
import static ru.starovoytov.home.toy.telegram.configuration.ConfigurationParametersHelper.TELEGRAM_PROXY_PORT;

/**
 * Конфигуратор сервиса
 *
 * @author starovoytov
 * @since 2020.02.16
 */
public final class Configurator extends AbstractConfigurator {
	/**
	 * Экземпляр конфигуратора
	 */
	private static final transient Configurator INSTANCE = new Configurator();

	/**
	 * Конструктор по умолчанию
	 */
	private Configurator() {
		super();
	}

	/**
	 * Получить экземпляр конфигуратора
	 *
	 * @return экземпляр конфигуратора
	 */
	public static Configurator getInstance() {
		return INSTANCE;
	}

	@Override
	protected void fillDefaultParameters() {
		setDefaultParameter(PORT, "10002");
		setDefaultParameter(HOST, "localhost");
	}

	/**
	 * Получить хост прослушивания сервиса
	 *
	 * @return хост прослушивания сервиса
	 */
	public String getHost() {
		return getStringParameter(HOST);
	}

	/**
	 * Получить порт прослушивания сервиса
	 *
	 * @return порт прослушивания сервиса
	 */
	public int getPort() {
		return getIntParameter(PORT);
	}

	/**
	 * Получить хост для прокси телеграма
	 *
	 * @return хост для прокси телеграма
	 */
	public String getTelegramProxyHost() {
		return getStringParameter(TELEGRAM_PROXY_HOST);
	}

	/**
	 * Получить порт для прокси телеграма
	 *
	 * @return порт для прокси телеграма
	 */
	public int getTelegramProxyPort() {
		return getIntParameter(TELEGRAM_PROXY_PORT);
	}
}
