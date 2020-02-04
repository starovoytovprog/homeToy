package ru.starovoytov.home.toy.springboot.library.controllers.log;

import ru.starovoytov.home.toy.common.libs.log.BaseLogMessageBuilder;

/**
 * Класс для создания структурированных логов библиотеки spring
 *
 * @author starovoytov
 * @since 2020.02.04
 */
public class SpringBootLibLogMessageBuilder extends BaseLogMessageBuilder {
	/**
	 * Инициализация построителя сообщений логов
	 *
	 * @return Построитель сообщений
	 */
	public static SpringBootLibLogMessageBuilder create() {
		return new SpringBootLibLogMessageBuilder();
	}
}
