package ru.starovoytov.home.toy.common.libs.log;

/**
 * Класс для создания структурированных логов общей библиотеки
 *
 * @author starovoytov
 * @since 2019.12.19
 */
public class CommonLogMessageBuilder extends BaseLogMessageBuilder {

	private static final String ADDRESS_URI = "addressUri";

	/**
	 * Конструктор строителя
	 */
	private CommonLogMessageBuilder() {
		super();
	}

	/**
	 * Инициализация построителя сообщений логов
	 *
	 * @return Построитель сообщений
	 */
	public static CommonLogMessageBuilder create() {
		return new CommonLogMessageBuilder();
	}

	/**
	 * Добавляем адрес обращения http-клиента
	 *
	 * @param uri uri-адрес
	 * @return Построитель сообщений
	 */
	public BaseLogMessageBuilder addUri(String uri) {
		super.add(ADDRESS_URI, uri);
		return this;
	}
}
