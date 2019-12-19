package ru.starovoytov.home.toy.web.library.log;

import ru.starovoytov.home.toy.common.libs.log.BaseLogMessageBuilder;

/**
 * Класс для создания структурированных логов
 *
 * @author starovoytov
 * @since 2019.12.19
 */
public class WebServiceLogMessageBuilder extends BaseLogMessageBuilder {
	/**
	 * Конструктор строителя
	 */
	private WebServiceLogMessageBuilder() {
		super();
	}

	/**
	 * Инициализация построителя сообщений логов
	 *
	 * @return Построитель сообщений
	 */
	public static WebServiceLogMessageBuilder create() {
		return new WebServiceLogMessageBuilder();
	}
}
