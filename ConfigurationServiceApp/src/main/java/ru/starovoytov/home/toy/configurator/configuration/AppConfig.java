package ru.starovoytov.home.toy.configurator.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.starovoytov.home.toy.common.libs.cache.UpdatedCache;
import ru.starovoytov.home.toy.configurator.controllers.ParametersCache;

import java.util.HashMap;
import java.util.Map;

/**
 * Конфигурация приложения
 *
 * @author starovoytov
 * @since 2020.02.02
 */
@Configuration
@SuppressWarnings({"PMD.AtLeastOneConstructor", "PMD.UseConcurrentHashMap"})
public class AppConfig {

	private static final ParametersCache PARAMETERS_CACHE;

	static {
		final Map<String, Object> params = new HashMap<>();
		params.put(ParametersCache.FILE_NAME, Configurator.getInstance().getParametersFile());
		PARAMETERS_CACHE = new ParametersCache(0, params);
	}

	@Bean(name = "Configurator")
	public Configurator getConfigurator() {
		return Configurator.getInstance();
	}

	@Bean(name = "ParametersCache")
	public ParametersCache getParametersCache() {
		return PARAMETERS_CACHE;
	}

	@Bean(name = "Caches")
	public Map<String, UpdatedCache> getCaches() {
		final Map<String, UpdatedCache> caches = new HashMap<>();
		caches.put("parametersCache", getParametersCache());
		return caches;
	}
}
