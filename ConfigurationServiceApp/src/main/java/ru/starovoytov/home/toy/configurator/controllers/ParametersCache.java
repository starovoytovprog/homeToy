package ru.starovoytov.home.toy.configurator.controllers;

import ru.starovoytov.home.toy.common.libs.cache.AbstractUpdatedCache;
import ru.starovoytov.home.toy.common.libs.exceptions.UpdateCacheException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;

/**
 * Кэш параметров
 *
 * @author starovoytov
 * @since 2020.02.08
 */
public class ParametersCache extends AbstractUpdatedCache<Properties> {

	/**
	 * Путь к файлу свойств
	 */
	private transient Path propertyFile;

	/**
	 * Конструктор кэша
	 *
	 * @param updateInterval интервал автоматического обновления кэша
	 * @param params         дополнительные параметры
	 */
	protected ParametersCache(final long updateInterval, final Map<String, Object> params) {
		super(updateInterval, params);
	}

	@Override
	@SuppressWarnings({"PMD.LawOfDemeter"})
	protected void setParams(final Map<String, Object> params) {
		this.propertyFile = Paths.get(params.get("propertyFile").toString());
	}

	@Override
	@SuppressWarnings({"PMD.LawOfDemeter"})
	protected Properties getNewEntity() throws UpdateCacheException {
		final Properties properties = new Properties();
		try (InputStream inputStream = Files.newInputStream(propertyFile)) {
			properties.load(inputStream);
		} catch (IOException ex) {
			throw new UpdateCacheException("Ошибка обновления кэша: " + ParametersCache.class.getCanonicalName(), ex);
		}
		return properties;
	}
}
