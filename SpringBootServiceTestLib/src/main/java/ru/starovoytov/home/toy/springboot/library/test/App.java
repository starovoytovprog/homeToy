package ru.starovoytov.home.toy.springboot.library.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Класс приложения для тестов SpringBoot
 *
 * @author starovoytov
 * @since 2020.02.01
 */
@SpringBootApplication
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
