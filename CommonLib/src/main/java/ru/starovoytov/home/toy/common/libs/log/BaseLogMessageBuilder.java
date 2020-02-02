package ru.starovoytov.home.toy.common.libs.log;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для создания структурированных логов
 *
 * @author starovoytov
 * @since 2019.12.19
 */
public class BaseLogMessageBuilder {
	/**
	 * Поле для произвольного текста
	 */
	private static final String MSG = "msg";
	/**
	 * Поле для идентификатора операции
	 */
	private static final String UID = "uid";
	/**
	 * Поле для времени выполнения операции
	 */
	private static final String OPERATION_TIME = "operationTime";
	/**
	 * Поле для времени выполнения операции
	 */
	private static final String JSON_MESSAGE = "jsonMessage";

	/**
	 * Контейнер аргументов для создания сообщения
	 */
	@SuppressWarnings({"PMD.UseConcurrentHashMap"})
	private final transient Map<String, Object> arguments = new HashMap<>();

	/**
	 * Инициализация строителя сообщений
	 */
	protected BaseLogMessageBuilder() {
	}

	/**
	 * Добавляем поля к сообщению
	 *
	 * @param field поле сообщения
	 * @param value значение
	 * @return Построитель сообщений
	 */
	public BaseLogMessageBuilder add(final String field, final String value) {
		arguments.put(field, value);
		return this;
	}

	/**
	 * Добавляем произвольный текст к сообщению
	 *
	 * @param msg произвольный текст
	 * @return Построитель сообщений
	 */
	public BaseLogMessageBuilder addMsg(final String msg) {
		arguments.put(MSG, msg);
		return this;
	}

	/**
	 * Добавляем идентификатор операции к сообщению
	 *
	 * @param uid идентификатор операции
	 * @return Построитель сообщений
	 */
	public BaseLogMessageBuilder addUid(final String uid) {
		arguments.put(UID, uid);
		return this;
	}

	/**
	 * Добавляем время выполнениея операции к сообщению
	 *
	 * @param operationTime время выполнениея операции
	 * @return Построитель сообщений
	 */
	public BaseLogMessageBuilder addOperationTime(final long operationTime) {
		arguments.put(OPERATION_TIME, operationTime);
		return this;
	}

	/**
	 * Получить сообщение
	 *
	 * @return Сообщение лога или сообщение о проблеме с конвертацией JSON.
	 */
	public String build() {
		return arguments.toString();
	}
}
