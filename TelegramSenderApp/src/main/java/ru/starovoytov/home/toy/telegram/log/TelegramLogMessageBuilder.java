package ru.starovoytov.home.toy.telegram.log;

import ru.starovoytov.home.toy.common.libs.log.BaseLogMessageBuilder;

/**
 * Класс для создания структурированных логов отправителя телеграм
 *
 * @author starovoytov
 * @since 2020.02.20
 */
public final class TelegramLogMessageBuilder extends BaseLogMessageBuilder {
	/**
	 * Конструктор строителя
	 */
	private TelegramLogMessageBuilder() {
		super();
	}

	/**
	 * Инициализация построителя сообщений логов
	 *
	 * @return Построитель сообщений
	 */
	public static TelegramLogMessageBuilder create() {
		return new TelegramLogMessageBuilder();
	}
}
