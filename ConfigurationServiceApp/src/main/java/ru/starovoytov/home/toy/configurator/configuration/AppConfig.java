package ru.starovoytov.home.toy.configurator.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author starovoytov
 * @since 2020.02.02
 */
@Configuration
public class AppConfig {
	@Bean(name = "Configurator")
	public Configurator getConfigurator() {
		return Configurator.getInstance();
	}
}
