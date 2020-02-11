package ru.starovoytov.home.toy.common.libs.configuration;

/**
 * Тестовый конфигуратор
 *
 * @author starovoytov
 * @since 2020.02.11
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
public class TestConfigurator extends AbstractConfigurator {
	/**
	 * Ключ тестового параметра
	 */
	public static final String DEFAULT = "DEFAULT";
	public static final String DEFAULT_VALUE = "defaultValue";
	public static final String DEFAULT_KEY = "defaultKey";

	@Override
	protected void fillDefaultParameters() {
		setDefaultParameter(DEFAULT, DEFAULT_VALUE);
		setDefaultParameter(DEFAULT_KEY + "1", DEFAULT_VALUE + "1");
		setDefaultParameter(DEFAULT_KEY + "2", DEFAULT_VALUE + "2");
		setDefaultParameter(DEFAULT_KEY + "3", DEFAULT_VALUE + "3");
		setDefaultParameter("SQL_PASSWORD", "123456");
		setDefaultParameter("int", "10");
	}

	public int getInt() {
		return getIntParameter("int");
	}

	public String getNull() {
		return getStringParameter("null parameter");
	}

	public String getDefault() {
		return getStringParameter(DEFAULT);
	}

	public void setDefault(final String value) {
		setDefaultParameter(DEFAULT, value);
	}

	public String getDefault1() {
		return getStringParameter(DEFAULT_KEY + "1");
	}

	public String getDefault2() {
		return getStringParameter(DEFAULT_KEY + "2");
	}

	public String getDefault3() {
		return getStringParameter(DEFAULT_KEY + "3");
	}
}
