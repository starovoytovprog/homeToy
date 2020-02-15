package ru.starovoytov.home.toy.vk.configuration;

import ru.starovoytov.home.toy.common.libs.configuration.AbstractConfigurator;

import static ru.starovoytov.home.toy.common.libs.configuration.ConfigurationParametersHelper.HOST;
import static ru.starovoytov.home.toy.common.libs.configuration.ConfigurationParametersHelper.PORT;
import static ru.starovoytov.home.toy.vk.configuration.ConfigurationParametersHelper.VK_ACCESS_TOKEN;
import static ru.starovoytov.home.toy.vk.configuration.ConfigurationParametersHelper.VK_USER_ID;

/**
 * Конфигуратор сервиса
 *
 * @author starovoytov
 * @since 2020.02.12
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
		setDefaultParameter(PORT, "10001");
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
	 * Получить id пользователья ВК
	 *
	 * @return id пользователья ВК
	 */
	public int getVkUserId() {
		return getIntParameter(VK_USER_ID);
	}

	/**
	 * Получить токен доступа в ВК
	 *
	 * @return токен доступа в ВК
	 */
	public String getVkAccessToken() {
		return getStringParameter(VK_ACCESS_TOKEN);
	}
}
