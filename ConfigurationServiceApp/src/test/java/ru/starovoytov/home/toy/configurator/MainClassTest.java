package ru.starovoytov.home.toy.configurator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.starovoytov.home.toy.configurator.configuration.Configurator;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.starovoytov.home.toy.test.utils.TestUtils.getFreePort;

/**
 * Тест сервиса {@link MainClass}
 *
 * @author starovoytov
 * @since 2019.12.21
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
class MainClassTest {

	/**
	 * Параметры для main
	 */
	private static final String[] ARGS = new String[0];

	/**
	 * Порт для тестов
	 */
	private static int servicePort;

	@BeforeAll
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public static void configureAndStart() {
		servicePort = getFreePort();
		Configurator.getInstance().setFinalParameter("PORT", Integer.toString(servicePort));
		Configurator.getInstance().clearParameters();
		MainClass.main(ARGS);
	}

	@Test
	@SuppressWarnings({"PMD.UseAssertSameInsteadOfAssertTrue"})
	public void doubleStartTest() {
		final MainClass mainClass = MainClass.getInstance();
		MainClass.main(ARGS);
		assertTrue(mainClass == MainClass.getInstance(), "Create new main class");
	}

	@AfterAll
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public static void stop() {
		MainClass.getInstance().stop();
	}
}
