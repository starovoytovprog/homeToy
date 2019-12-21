package ru.starovoytov.home.toy.common.libs.configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Конфигуратор
 *
 * @author starovoytov
 * @since 2019.12.20
 */
public abstract class AbstractConfigurator {
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
	protected String getStringParameter(final String key) {
		String value = parameters.get(key);
		if (value == null) {
			value = finalParameters.get(key);
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
}
