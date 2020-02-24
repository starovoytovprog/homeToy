package ru.starovoytov.home.toy.telegram.bot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;
import ru.starovoytov.home.toy.common.libs.exceptions.RabbitMqException;
import ru.starovoytov.home.toy.common.libs.rabbit.RabbitMqHelper;
import ru.starovoytov.home.toy.common.libs.rabbit.RabbitMqInstanceParameters;
import ru.starovoytov.home.toy.telegram.configuration.Configurator;
import ru.starovoytov.home.toy.telegram.exceptions.TelegramConnectionException;
import ru.starovoytov.home.toy.telegram.log.TelegramLogMessageBuilder;

import static ru.starovoytov.home.toy.telegram.log.MarkersHelper.TELEGRAM_CONNECT;

/**
 * Конструктор телеграм-бота
 *
 * @author starovoytov
 * @since 2020.02.20
 */
public class TelegramBuilder {
	private static final Logger LOGGER = LogManager.getLogger(TelegramBuilder.class);

	private transient String botName;
	private transient String botToken;
	private transient RabbitMqInstanceParameters rabbitParameters;
	private transient String rabbitQueueName;

	/**
	 * Конструктор по умолчанию
	 */
	public TelegramBuilder() {
		ApiContextInitializer.init();
	}

	/**
	 * Добавить имя бота
	 *
	 * @param botName имя бота
	 * @return экземпляр конструктора
	 */
	public TelegramBuilder addBotName(final String botName) {
		this.botName = botName;
		return this;
	}

	/**
	 * Добавить токен бота
	 *
	 * @param botToken токен бота
	 * @return экземпляр конструктора
	 */
	public TelegramBuilder addBotToken(final String botToken) {
		this.botToken = botToken;
		return this;
	}

	/**
	 * Добавить параметры рэббита
	 *
	 * @param rabbitParameters параметры рэббита
	 * @return экземпляр конструктора
	 */
	public TelegramBuilder addRabbitParameters(final RabbitMqInstanceParameters rabbitParameters) {
		this.rabbitParameters = rabbitParameters;
		return this;
	}

	/**
	 * Добавить имя очереди рэббита
	 *
	 * @param rabbitQueueName имя очереди рэббита
	 * @return экземпляр конструктора
	 */
	public TelegramBuilder addRabbitQueueName(final String rabbitQueueName) {
		this.rabbitQueueName = rabbitQueueName;
		return this;
	}

	/**
	 * Собрать
	 *
	 * @throws RabbitMqException           ошибка соединения с rabbit
	 * @throws TelegramConnectionException ошибка соединения с telegram
	 */
	@SuppressWarnings({"PMD.LawOfDemeter", "PMD.CloseResource"})
	public void build() throws RabbitMqException, TelegramConnectionException {
		final DefaultBotOptions options = new DefaultBotOptions();
		options.setProxyType(DefaultBotOptions.ProxyType.HTTP);
		options.setProxyHost(Configurator.getInstance().getTelegramProxyHost());
		options.setProxyPort(Configurator.getInstance().getTelegramProxyPort());
		final TelegramBot bot = new TelegramBot(options, botName, botToken);
		try {
			new TelegramBotsApi().registerBot(bot);
		} catch (TelegramApiRequestException ex) {
			LOGGER.error(TELEGRAM_CONNECT, () -> TelegramLogMessageBuilder.create()
				.addMsg("Ошибка соединения с telegram")
				.build(), ex);
			throw new TelegramConnectionException("Can't register bot", ex);
		}
		bot.sendMessage("Я снова живой!", Configurator.getInstance().getTelegramBotOwner());
		final TelegramCallBack callBack = new TelegramCallBack(bot);
		RabbitMqHelper.simpleReceiveStringMessages(rabbitParameters, rabbitQueueName, callBack);
	}
}
