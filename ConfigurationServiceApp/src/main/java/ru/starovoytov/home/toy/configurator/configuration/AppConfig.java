package ru.starovoytov.home.toy.configurator.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация приложения
 *
 * @author starovoytov
 * @since 2020.02.02
 */
@Configuration
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
public class AppConfig {
	@Bean(name = "Configurator")
	public Configurator getConfigurator() {
		return Configurator.getInstance();
	}
}
