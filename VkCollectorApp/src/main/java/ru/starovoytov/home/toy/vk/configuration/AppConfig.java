package ru.starovoytov.home.toy.vk.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.starovoytov.home.toy.common.libs.cache.UpdatedCache;

import java.util.HashMap;
import java.util.Map;

/**
 * Конфигурация приложения
 *
 * @author starovoytov
 * @since 2020.02.12
 */
@Configuration
@SuppressWarnings({"PMD.AtLeastOneConstructor", "PMD.UseConcurrentHashMap"})
public class AppConfig {
	@Bean(name = "Configurator")
	public Configurator getConfigurator() {
		return Configurator.getInstance();
	}

	@Bean(name = "Caches")
	public Map<String, UpdatedCache> getCaches() {
		return new HashMap<>();
	}
}
