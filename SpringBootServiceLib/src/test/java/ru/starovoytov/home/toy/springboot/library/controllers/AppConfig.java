package ru.starovoytov.home.toy.springboot.library.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.starovoytov.home.toy.common.libs.cache.UpdatedCache;

import java.util.HashMap;
import java.util.Map;

/**
 * Конфигурация запуска теста
 *
 * @author starovoytov
 * @since 2020.02.02
 */
@Configuration
@SuppressWarnings({"PMD.AtLeastOneConstructor", "PMD.UseUtilityClass"})
public class AppConfig {
	@Bean(name = "Configurator")
	public Configurator getConfigurator() {
		return Configurator.getInstance();
	}

	@Bean(name = "Caches")
	@SuppressWarnings({"PMD.UseConcurrentHashMap"})
	public Map<String, UpdatedCache> getCaches() {
		final Map<String, UpdatedCache> caches = new HashMap<>();
		caches.put("good", new TestCache());
		return caches;
	}

	/**
	 * Класс для теста кэша
	 */
	private static class TestCache implements UpdatedCache {
		@Override
		public Object getEntity() {
			return "ok";
		}

		@Override
		public void update() {
		}

		@Override
		@SuppressWarnings({"PMD.LawOfDemeter"})
		public String displayEntity() {
			return getEntity().toString();
		}
	}
}
