package ru.starovoytov.home.toy.common.libs.resource;

import org.junit.jupiter.api.Test;
import ru.starovoytov.home.toy.common.libs.exceptions.ResourceException;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.starovoytov.home.toy.common.libs.resource.ResourceHelper.getFileContentFromResources;
import static ru.starovoytov.home.toy.common.libs.resource.ResourceHelper.getFileUrlFromResources;

/**
 * Тестирование помощника для работы с ресурсами {@link ResourceHelper}
 *
 * @author starovoytov
 * @since 2020.01.07
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor", "PMD.TooManyStaticImports", "PMD.AvoidDuplicateLiterals"})
class ResourceHelperTest {

	/**
	 * Тест получения пути к файлу ресурсов
	 *
	 * @throws ResourceException Ошибка пути к ресурсу
	 */
	@Test
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public void fileUrlFromResourcesTest() throws ResourceException {
		assertTrue(new File(getFileUrlFromResources("/test.txt")).exists(), "Bad resource path.");
	}

	/**
	 * Тест исключения при получении пути к файлу ресурсов
	 */
	@Test
	@SuppressWarnings({"PMD.LawOfDemeter", "PMD.JUnitTestContainsTooManyAsserts"})
	public void fileUrlFromResourcesExceptionTest() {
		final Exception exception = assertThrows(ResourceException.class, () -> getFileUrlFromResources("test.txt"));
		assertEquals("Bad path: test.txt", exception.getMessage(), "Bad exception message");
	}

	/**
	 * Тест получения контента из файла ресурсов
	 *
	 * @throws ResourceException Ошибка пути к ресурсу
	 */
	@Test
	public void fileContentFromResourcesTest() throws ResourceException {
		assertEquals("Test resource content.\nNext line.", getFileContentFromResources("/test.txt"), "Bad resource content.");
	}

	/**
	 * Тест исключения при получении контента из файла ресурсов
	 */
	@Test
	@SuppressWarnings({"PMD.LawOfDemeter", "PMD.JUnitTestContainsTooManyAsserts"})
	public void fileContentFromResourcesExceptionTest() {
		final Exception exception = assertThrows(ResourceException.class, () -> getFileContentFromResources("test.txt"));
		assertEquals("Bad path: test.txt", exception.getMessage(), "Bad exception message");
	}

	/**
	 * Тест получения контента из путсого файла ресурсов
	 *
	 * @throws ResourceException Ошибка пути к ресурсу
	 */
	@Test
	@SuppressWarnings({"PMD.LawOfDemeter", "PMD.JUnitTestContainsTooManyAsserts"})
	public void fileContentEmptyFromResourcesTest() throws ResourceException {
		assertEquals("", getFileContentFromResources("/emptyTest.txt"), "Bad resource content.");
	}
}