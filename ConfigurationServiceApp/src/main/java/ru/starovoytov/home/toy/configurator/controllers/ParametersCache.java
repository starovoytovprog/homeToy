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
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.starovoytov.home.toy.common.libs.helpers.MapHelper.mapToString;

/**
 * Кэш параметров
 *
 * @author starovoytov
 * @since 2020.02.08
 */
public class ParametersCache extends AbstractUpdatedCache<Properties> {

	public static final String FILE_NAME = "FILE_NAME";

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
	public ParametersCache(final long updateInterval, final Map<String, Object> params) {
		super(updateInterval, params);
	}

	@Override
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public String displayEntity() {
		final Stream<Map.Entry<Object, Object>> stream = getEntity().entrySet().stream();
		final Map<String, String> mapOfProperties = stream.collect(Collectors.toMap(e -> String.valueOf(e.getKey()), e -> String
			.valueOf(e.getValue())));
		final StringBuilder builder = new StringBuilder().append("Parameters:\n\n")
			.append(mapToString(mapOfProperties));
		return builder.toString();
	}

	@Override
	@SuppressWarnings({"PMD.LawOfDemeter"})
	protected void setParams(final Map<String, Object> params) {
		if (params.get(FILE_NAME) != null) {
			this.propertyFile = Paths.get(params.get(FILE_NAME).toString());
		}
	}

	@Override
	@SuppressWarnings({"PMD.LawOfDemeter"})
	protected Properties getNewEntity() throws UpdateCacheException {
		if (propertyFile == null) {
			throw new UpdateCacheException("Не задан файл параметров: " + ParametersCache.class.getCanonicalName(), null);
		}

		final Properties properties = new Properties();
		try (InputStream inputStream = Files.newInputStream(propertyFile)) {
			properties.load(inputStream);
		} catch (IOException ex) {
			throw new UpdateCacheException("Ошибка обновления кэша: " + ParametersCache.class.getCanonicalName(), ex);
		}
		return properties;
	}
}
