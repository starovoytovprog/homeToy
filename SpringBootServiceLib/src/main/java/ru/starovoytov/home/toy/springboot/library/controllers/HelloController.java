package ru.starovoytov.home.toy.springboot.library.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для отображения информации о сервисе
 *
 * @author starovoytov
 * @since 2020.02.02
 */
@RestController("/hello")
@RequestMapping("hello")
public class HelloController {
	@GetMapping
	public String helloGradle() {
		return new StringBuilder("Hello!").toString();
	}
}
