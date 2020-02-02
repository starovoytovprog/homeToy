package ru.starovoytov.home.toy.springboot.library.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Тестовый контроллер
 *
 * @author starovoytov
 * @since 2020.02.02
 */
@RestController("/")
public class TestController {
	@GetMapping
	public String helloGradle() {
		return "Hello Gradle!";
	}
}
