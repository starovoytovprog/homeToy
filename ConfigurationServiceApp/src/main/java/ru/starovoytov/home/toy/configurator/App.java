package ru.starovoytov.home.toy.configurator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Стартовый класс сервиса конфигурации
 *
 * @author starovoytov
 * @since 2019.12.19
 */
@SpringBootApplication
@ComponentScan({"ru.starovoytov.home.toy.springboot.library.controllers", "ru.starovoytov.home.toy.configurator"})
public class App {
	/**
	 * Стартовый метод
	 *
	 * @param args аргументы
	 */
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
