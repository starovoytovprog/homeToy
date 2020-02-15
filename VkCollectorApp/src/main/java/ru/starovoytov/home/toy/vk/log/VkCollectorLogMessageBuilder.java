package ru.starovoytov.home.toy.vk.log;

import ru.starovoytov.home.toy.common.libs.log.BaseLogMessageBuilder;

/**
 * Класс для создания структурированных логов сервиса сбора сообщений из ВК
 *
 * @author starovoytov
 * @since 2020.02.15
 */
public class VkCollectorLogMessageBuilder extends BaseLogMessageBuilder {
	/**
	 * Инициализация построителя сообщений логов
	 *
	 * @return Построитель сообщений
	 */
	public static VkCollectorLogMessageBuilder create() {
		return new VkCollectorLogMessageBuilder();
	}
}
