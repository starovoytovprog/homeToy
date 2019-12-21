package ru.starovoytov.home.toy.common.libs.configuration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест конфигуратора
 *
 * @author starovoytov
 * @since 2019.12.21
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
class ConfiguratorTest {
	/**
	 * Ключ тестового параметра
	 */
	private static final String DEFAULT = "DEFAULT";

	/**
	 * Получение значения по умолчанию
	 */
	@Test
	public void default1Test() {
		final TestConfigurator configurator = new TestConfigurator();
		assertEquals("defaultValue", configurator.getDefault(), "Not default value");
	}

	/**
	 * Получение значения по умолчанию
	 */
	@Test
	public void default2Test() {
		final TestConfigurator configurator = new TestConfigurator();
		configurator.getDefault();
		configurator.setDefault("value2");
		assertEquals("defaultValue", configurator.getDefault(), "New default value");
	}

	/**
	 * Получение значения по умолчанию
	 */
	@Test
	public void default3Test() {
		final TestConfigurator configurator = new TestConfigurator();
		configurator.getDefault();
		configurator.setDefault("value3");
		configurator.clearParameters();
		assertEquals("value3", configurator.getDefault(), "Not new default value");
	}

	/**
	 * Получение фиксированного значения
	 */
	@Test
	public void fixedTest() {
		final TestConfigurator configurator = new TestConfigurator();
		configurator.getDefault();
		configurator.setDefault("value4");
		configurator.setFinalParameter(DEFAULT, "fixed");
		configurator.clearParameters();
		assertEquals("fixed", configurator.getDefault(), "Not fixed value");
	}

	/**
	 * Тестовый конфигуратор
	 */
	private static class TestConfigurator extends AbstractConfigurator {
		@Override
		protected void fillDefaultParameters() {
			setDefaultParameter(DEFAULT, "defaultValue");
		}

		public String getDefault() {
			return getStringParameter(DEFAULT);
		}

		public void setDefault(final String value) {
			setDefaultParameter(DEFAULT, value);
		}
	}
}
