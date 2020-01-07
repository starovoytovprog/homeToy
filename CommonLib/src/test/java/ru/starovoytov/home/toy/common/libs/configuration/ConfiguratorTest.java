package ru.starovoytov.home.toy.common.libs.configuration;

import org.junit.jupiter.api.Test;
import ru.starovoytov.home.toy.common.libs.exceptions.ResourceException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.starovoytov.home.toy.common.libs.resource.ResourceHelper.getFileContentFromResources;
import static ru.starovoytov.home.toy.test.utils.TestUtils.setEnv;

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
	private static final String DEFAULT_VALUE = "defaultValue";
	private static final String DEFAULT_KEY = "defaultKey";

	/**
	 * Получение значения по умолчанию
	 */
	@Test
	public void default1Test() {
		final TestConfigurator configurator = new TestConfigurator();
		assertEquals(DEFAULT_VALUE, configurator.getDefault(), "Not default value");
	}

	/**
	 * Получение значения переменной окружения
	 */
	@Test
	public void envTest() {
		final TestConfigurator configurator = new TestConfigurator();
		setEnv(DEFAULT_VALUE, "env value");
		assertEquals(DEFAULT_VALUE, configurator.getDefault(), "Not default value");
	}

	/**
	 * Получение значения по умолчанию
	 */
	@Test
	public void default2Test() {
		final TestConfigurator configurator = new TestConfigurator();
		configurator.getDefault();
		configurator.setDefault("value2");
		assertEquals(DEFAULT_VALUE, configurator.getDefault(), "New default value");
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
		setEnv(DEFAULT_VALUE, "env value");
		assertEquals("fixed", configurator.getDefault(), "Not fixed value");
	}

	/**
	 * Тест преобразования конфигуратора в строку
	 *
	 * @throws ResourceException ошибка обращения к ресурсу
	 */
	@Test
	public void stringTest() throws ResourceException {
		setEnv(DEFAULT_KEY + "1", "envKey1");
		setEnv(DEFAULT_KEY + "2", "envKey2");
		final TestConfigurator configurator = new TestConfigurator();
		configurator.setFinalParameter("defaultKey1", "finalValue1");
		configurator.getDefault();
		configurator.getDefault1();
		configurator.getDefault2();
		configurator.getDefault3();
		final String confString = getFileContentFromResources("/configuratorToString.txt");
		assertEquals(confString, configurator.toString(), "Bad configurator string");
	}

	/**
	 * Тестовый конфигуратор
	 */
	private static class TestConfigurator extends AbstractConfigurator {
		@Override
		protected void fillDefaultParameters() {
			setDefaultParameter(DEFAULT, DEFAULT_VALUE);
			setDefaultParameter(DEFAULT_KEY + "1", DEFAULT_VALUE + "1");
			setDefaultParameter(DEFAULT_KEY + "2", DEFAULT_VALUE + "2");
			setDefaultParameter(DEFAULT_KEY + "3", DEFAULT_VALUE + "3");
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
}
