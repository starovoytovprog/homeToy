package ru.starovoytov.home.toy.springboot.library.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
