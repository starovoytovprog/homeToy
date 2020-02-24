package ru.starovoytov.home.toy.telegram.bot;

import com.rabbitmq.client.Delivery;
import ru.starovoytov.home.toy.common.libs.rabbit.AbstractMessageReceiver;
import ru.starovoytov.home.toy.telegram.configuration.Configurator;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Колл-бэк для отправки полученных сообщений в telegram
 *
 * @author starovoytov
 * @since 2020.02.20
 */
public class TelegramCallBack extends AbstractMessageReceiver {

	private final transient TelegramBot telegramBot;

	/**
	 * Конструктор
	 *
	 * @param telegramBot Телеграм-бот
	 */
	public TelegramCallBack(final TelegramBot telegramBot) {
		super();
		this.telegramBot = telegramBot;
	}

	@Override
	@SuppressWarnings({"PMD.LawOfDemeter", "PMD.AvoidInstantiatingObjectsInLoops"})
	public void handle(final String consumerTag, final Delivery message) throws IOException {
		for (final String chatStringId : Configurator.getInstance().getTelegramChatId().split(";")) {
			telegramBot.sendMessage(new String(message.getBody(), Charset.defaultCharset()), Long.parseLong(chatStringId));
		}
	}
}
