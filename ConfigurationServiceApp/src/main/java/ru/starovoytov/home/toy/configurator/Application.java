package ru.starovoytov.home.toy.configurator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ru.starovoytov.home.toy.configurator.configuration.Configurator;

import java.util.HashMap;
import java.util.Map;

/**
 * Стартовый класс сервиса конфигурации
 *
 * @author starovoytov
 * @since 2019.12.19
 */
@SpringBootApplication
@ComponentScan({"ru.starovoytov.home.toy.springboot.library.controllers", "ru.starovoytov.home.toy.configurator"})
@SuppressWarnings({"PMD.UseUtilityClass"})
public class Application {
	/**
	 * Стартовый метод
	 *
	 * @param args аргументы
	 */
	public static void main(final String[] args) {
		final SpringApplication app = new SpringApplication(Application.class);
		final Map<String, Object> properties = new HashMap<>();
		properties.put("server.port", Configurator.getInstance().getPort());
		properties.put("server.host", Configurator.getInstance().getHost());
		app.setDefaultProperties(properties);
		app.run(args);
	}
}
