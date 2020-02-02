package ru.starovoytov.home.toy.springboot.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.starovoytov.home.toy.common.libs.configuration.AbstractConfigurator;

/**
 * Контроллер для отображения информации о сервисе
 *
 * @author starovoytov
 * @since 2020.02.02
 */
@RestController("/hello")
@RequestMapping("hello")
public class HelloController {

	@Autowired
	@Qualifier("Configurator")
	private AbstractConfigurator configurator;

	@GetMapping
	public String helloGradle() {
		return new StringBuilder("Hello!").toString();
	}
}
