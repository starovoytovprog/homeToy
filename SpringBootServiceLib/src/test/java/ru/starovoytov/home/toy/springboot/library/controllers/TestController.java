package ru.starovoytov.home.toy.springboot.library.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Тестовый контроллер
 *
 * @author starovoytov
 * @since 2020.02.02
 */
@RestController("/")
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
public class TestController {
	@GetMapping
	public String helloGradle() {
		return "Hello Gradle!";
	}
}
