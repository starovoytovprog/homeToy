package ru.starovoytov.home.toy.configurator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

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

	@BeforeAll
	public static void configureAndStart() {
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
