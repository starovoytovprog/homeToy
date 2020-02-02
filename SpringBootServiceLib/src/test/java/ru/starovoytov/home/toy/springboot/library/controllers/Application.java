package ru.starovoytov.home.toy.springboot.library.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Класс приложения для тестов SpringBoot
 *
 * @author starovoytov
 * @since 2020.02.01
 */
@SpringBootApplication
@SuppressWarnings({"PMD.UseUtilityClass"})
public class Application {
	/**
	 * Стартовый метод
	 *
	 * @param args аргументы
	 */
	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
