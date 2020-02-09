package ru.starovoytov.home.toy.configurator.controllers;

import org.junit.jupiter.api.Test;
import ru.starovoytov.home.toy.common.libs.exceptions.ResourceException;
import ru.starovoytov.home.toy.common.libs.resource.ResourceHelper;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест кэша параметров
 *
 * @author starovoytov
 * @since 2020.02.08
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor", "PMD.UseConcurrentHashMap"})
class ParametersCacheTest {
	/**
	 * Тест не переданного параметра
	 */
	@Test
	public void testBadFile1() {
		final Map<String, Object> params = new HashMap<>();
		final ParametersCache parametersCache = new ParametersCache(0, params);
		assertEquals(null, parametersCache.getEntity(), "Not null value");
	}

	/**
	 * Тест отсутствующего файла
	 */
	@Test
	public void testBadFile2() {
		final Map<String, Object> params = new HashMap<>();
		params.put(ParametersCache.FILE_NAME, "It's bad file name");
		final ParametersCache parametersCache = new ParametersCache(0, params);
		assertEquals(null, parametersCache.getEntity(), "Not null value");
	}

	/**
	 * Тест файла параметров
	 *
	 * @throws ResourceException ошибка обращения к ресурсу
	 */
	@Test
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public void testFile1() throws ResourceException {
		final Map<String, Object> params = new HashMap<>();
		params.put(ParametersCache.FILE_NAME, ResourceHelper.getFileUrlFromResources("/test.properties"));
		final ParametersCache parametersCache = new ParametersCache(0, params);
		assertEquals("value1", parametersCache.getEntity().getProperty("key1"), "Bad value");
	}

	/**
	 * Тест файла параметров 2
	 *
	 * @throws ResourceException ошибка обращения к ресурсу
	 */
	@Test
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public void testFile2() throws ResourceException {
		final Map<String, Object> params = new HashMap<>();
		params.put(ParametersCache.FILE_NAME, ResourceHelper.getFileUrlFromResources("/test.properties"));
		final ParametersCache parametersCache = new ParametersCache(0, params);
		assertEquals("value2", parametersCache.getEntity().getProperty("key2"), "Bad value");
	}
}