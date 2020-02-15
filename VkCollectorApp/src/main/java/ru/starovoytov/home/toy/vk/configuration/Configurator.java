package ru.starovoytov.home.toy.vk.configuration;

import ru.starovoytov.home.toy.common.libs.configuration.AbstractConfigurator;
import ru.starovoytov.home.toy.common.libs.rabbit.RabbitMqInstanceParameters;

import static ru.starovoytov.home.toy.common.libs.configuration.ConfigurationParametersHelper.HOST;
import static ru.starovoytov.home.toy.common.libs.configuration.ConfigurationParametersHelper.PORT;
import static ru.starovoytov.home.toy.vk.configuration.ConfigurationParametersHelper.VK_ACCESS_TOKEN;
import static ru.starovoytov.home.toy.vk.configuration.ConfigurationParametersHelper.VK_RABBIT_QUEUE_HOST;
import static ru.starovoytov.home.toy.vk.configuration.ConfigurationParametersHelper.VK_RABBIT_QUEUE_LOGIN;
import static ru.starovoytov.home.toy.vk.configuration.ConfigurationParametersHelper.VK_RABBIT_QUEUE_NAME;
import static ru.starovoytov.home.toy.vk.configuration.ConfigurationParametersHelper.VK_RABBIT_QUEUE_PASSWORD;
import static ru.starovoytov.home.toy.vk.configuration.ConfigurationParametersHelper.VK_RUN_COLLECT;
import static ru.starovoytov.home.toy.vk.configuration.ConfigurationParametersHelper.VK_UPDATE_INTERVAL;
import static ru.starovoytov.home.toy.vk.configuration.ConfigurationParametersHelper.VK_USER_ID;
import static ru.starovoytov.home.toy.vk.configuration.ConfigurationParametersHelper.VK_WALLS_ID;

/**
 * Конфигуратор сервиса
 *
 * @author starovoytov
 * @since 2020.02.12
 */
@SuppressWarnings({"PMD.TooManyStaticImports"})
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
		setDefaultParameter(VK_RUN_COLLECT, "false");
		setDefaultParameter(VK_UPDATE_INTERVAL, "60000");
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

	/**
	 * Получить список id стен для просмотра
	 *
	 * @return список id стен для просмотра
	 */
	public String getWallsId() {
		return getStringParameter(VK_WALLS_ID);
	}

	/**
	 * Получить признак сборки
	 *
	 * @return признак сборки
	 */
	public String getRunCollect() {
		return getStringParameter(VK_RUN_COLLECT);
	}

	/**
	 * Получить имя очереди rabbit
	 *
	 * @return имя очереди rabbit
	 */
	public String getRabbitQueueName() {
		return getStringParameter(VK_RABBIT_QUEUE_NAME);
	}

	/**
	 * Получиьт параметры подключения к rabbit
	 *
	 * @return параметры подключения к rabbit
	 */
	public RabbitMqInstanceParameters getRabbitMqInstanceParameters() {
		return new RabbitMqInstanceParameters(getStringParameter(VK_RABBIT_QUEUE_HOST), getStringParameter(VK_RABBIT_QUEUE_LOGIN), getStringParameter(VK_RABBIT_QUEUE_PASSWORD));
	}

	/**
	 * Получить интервал между временем проверки страниц в ВК
	 *
	 * @return интервал между временем проверки страниц в ВК
	 */
	public long getVkUpdateInterval() {
		return getIntParameter(VK_UPDATE_INTERVAL);
	}
}
