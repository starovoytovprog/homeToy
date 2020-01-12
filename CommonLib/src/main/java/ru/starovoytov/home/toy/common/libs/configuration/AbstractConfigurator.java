package ru.starovoytov.home.toy.common.libs.configuration;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Конфигуратор
 *
 * @author starovoytov
 * @since 2019.12.20
 */
public abstract class AbstractConfigurator {

	/**
	 * Ключ, оозначающий параметр-пароль
	 */
	private static final String PASSWORD_KEY = "password";

	/**
	 * Контейнер параметров
	 */
	private final transient Map<String, String> parameters;
	/**
	 * Контейнер параметров по умолчанию
	 */
	private final transient Map<String, String> defaultParameters;
	/**
	 * Контейнер фиксированных параметров
	 */
	private final transient Map<String, String> finalParameters;

	/**
	 * Время инициации конфигуратора
	 */
	private static final String START_TIME = new SimpleDateFormat("YYYY.MM.dd HH:mm:ss", Locale.getDefault()).format(new Date());

	/**
	 * Конструктор по умолчанию
	 */
	protected AbstractConfigurator() {
		this.parameters = new HashMap<>();
		this.defaultParameters = new HashMap<>();
		this.finalParameters = new HashMap<>();
		fillDefaultParameters();
	}

	/**
	 * Заполнение параметров по умолчанию
	 */
	protected abstract void fillDefaultParameters();

	/**
	 * Добавить значение по умолчанию
	 *
	 * @param key   ключ
	 * @param value значение
	 */
	protected void setDefaultParameter(final String key, final String value) {
		defaultParameters.put(key, value);
	}

	/**
	 * Добавить фиксированное значение
	 *
	 * @param key   ключ
	 * @param value значение
	 */
	public void setFinalParameter(final String key, final String value) {
		finalParameters.put(key, value);
	}

	/**
	 * Получить строковое значение по ключу
	 *
	 * @param key ключ
	 * @return значение
	 */
	@SuppressWarnings({"PMD.LawOfDemeter"})
	protected String getStringParameter(final String key) {
		String value = parameters.get(key);
		if (value == null) {
			value = finalParameters.get(key);
			if (value != null) {
				parameters.put(key, value);
			}
		}
		if (value == null) {
			value = System.getenv().get(key);
			if (value != null) {
				parameters.put(key, value);
			}
		}
		if (value == null) {
			value = defaultParameters.get(key);
			if (value != null) {
				parameters.put(key, value);
			}
		}
		return value;
	}

	/**
	 * Получить числовое значение по ключу
	 *
	 * @param key ключ
	 * @return значение
	 */
	protected int getIntParameter(final String key) {
		return Integer.parseInt(getStringParameter(key));
	}

	/**
	 * Сбросить параметры
	 */
	public void clearParameters() {
		parameters.clear();
	}

	/**
	 * Представление конфигурации в строке
	 *
	 * @return строка конфигурации
	 */
	@Override
	public String toString() {
		final StringBuilder stringBuilder = new StringBuilder(500);
		stringBuilder.append("Configurator: ")
			.append(this.getClass().toString())
			.append("\n\nActualValues:\n")
			.append(mapToString(parameters))
			.append("\n\nDefaultValues:\n")
			.append(mapToString(defaultParameters))
			.append("\n\nFinalValues:\n")
			.append(mapToString(finalParameters))
			.append("\n\nEnvValues:\n")
			.append(mapToString(System.getenv()));

		return stringBuilder.toString();
	}

	/**
	 * Получить время инициации конфигуратора
	 *
	 * @return время инициации конфигуратора
	 */
	public String getStartTime() {
		return START_TIME;
	}

	/**
	 * Формирование строки из мапы
	 *
	 * @param map мапа
	 * @return строка
	 */
	@SuppressWarnings({"PMD.LawOfDemeter"})
	private String mapToString(final Map<String, String> map) {
		final StringBuilder builder = new StringBuilder();

		for (final Map.Entry<String, String> entry : map.entrySet()) {
			if (!builder.toString().isEmpty()) {
				builder.append('\n');
			}
			builder.append(entry.getKey());
			builder.append('=');
			if (entry.getKey().toLowerCase(Locale.getDefault()).contains(PASSWORD_KEY)) {
				builder.append("***");
			} else {
				builder.append(entry.getValue());
			}
		}

		return builder.toString();
	}
}
