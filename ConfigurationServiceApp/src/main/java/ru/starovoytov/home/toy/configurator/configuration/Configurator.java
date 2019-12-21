package ru.starovoytov.home.toy.configurator.configuration;

import ru.starovoytov.home.toy.common.libs.configuration.AbstractConfigurator;

import static ru.starovoytov.home.toy.configurator.configuration.ConfigurationParametersHelper.HOST;
import static ru.starovoytov.home.toy.configurator.configuration.ConfigurationParametersHelper.PORT;

/**
 * Конфигуратор сервиса
 *
 * @author starovoytov
 * @since 2019.12.20
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
		setDefaultParameter(PORT, "10000");
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
}
