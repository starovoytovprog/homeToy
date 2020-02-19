package ru.starovoytov.home.toy.telegram.bot;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 * Реализация бота для телеграм
 *
 * @author starovoytov
 * @since 2020.02.17
 */
public class TelegramBot extends TelegramLongPollingBot {

	private final transient String botUserName;
	private final transient String botUserToken;

	/**
	 * Конструктор бота
	 *
	 * @param options      опции бота
	 * @param botUserName  имя бота
	 * @param botUserToken токен бота
	 */
	public TelegramBot(final DefaultBotOptions options, final String botUserName, final String botUserToken) {
		super(options);
		this.botUserName = botUserName;
		this.botUserToken = botUserToken;
	}

	@Override
	public void onUpdateReceived(final Update update) {
	}

	@Override
	public String getBotUsername() {
		return botUserName;
	}

	@Override
	public String getBotToken() {
		return botUserToken;
	}
}
