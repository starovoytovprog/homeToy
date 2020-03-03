package ru.starovoytov.home.toy.telegram.bot;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.starovoytov.home.toy.telegram.configuration.Configurator;
import ru.starovoytov.home.toy.telegram.log.TelegramLogMessageBuilder;

import static ru.starovoytov.home.toy.telegram.log.MarkersHelper.TELEGRAM_SEND_MESSAGE;

/**
 * Реализация бота для телеграм
 *
 * @author starovoytov
 * @since 2020.02.17
 */
public class TelegramBot extends TelegramLongPollingBot {
	private static final Logger LOGGER = LogManager.getLogger(TelegramBot.class);

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
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public void onUpdateReceived(final Update update) {
		if (update.getMessage().getChatId() == Configurator.getInstance().getTelegramBotOwner()) {
			updateProcess(update);
		}
	}

	/**
	 * Обработка входящих сообщений
	 *
	 * @param update входящее сообщение
	 */
	@SuppressWarnings({"PMD.LawOfDemeter"})
	private void updateProcess(final Update update) {
		sendMessage(update.getMessage().getText(), update.getMessage().getChatId());
	}

	@Override
	public String getBotUsername() {
		return botUserName;
	}

	@Override
	public String getBotToken() {
		return botUserToken;
	}

	/**
	 * Отправка сообщения в чат
	 *
	 * @param message Текст сообщения
	 * @param chatId  id чата
	 */
	public void sendMessage(final String message, final long chatId) {
		final SendMessage sendMessageObject = new SendMessage();
		sendMessageObject.setText(message);
		sendMessageObject.setChatId(chatId);

		boolean isSend = false;
		while (!isSend) {
			try {
				sendApiMethod(sendMessageObject);
				isSend = true;
			} catch (TelegramApiException ex) {
				LOGGER.error(TELEGRAM_SEND_MESSAGE, () -> TelegramLogMessageBuilder.create()
					.addMsg("Ошибка оправки сообщения")
					.build(), ex);
			}
		}
	}
}
