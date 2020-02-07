package ru.starovoytov.home.toy.configurator.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер получения значения параметра
 *
 * @author starovoytov
 * @since 2020.02.06
 */
@RestController("/get/value")
@RequestMapping("get/value")
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
public class GetParameterController {
	/**
	 * Получить значение параметра
	 *
	 * @param name имя параметра
	 * @return значение параметра
	 */
	@GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
	public String helloGradle(final String name) {
		return name;
	}
}
