package ru.starovoytov.home.toy.springboot.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
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

	/**
	 * Имя сервиса
	 */
	private static final String SERVICE_NAME = HelloController.class.getPackage().getImplementationTitle();

	/**
	 * Версия сервиса
	 */
	private static final String REVISION_HASH = HelloController.class.getPackage().getImplementationVersion();

	@GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
	public String helloGradle() {
		return new StringBuilder(500).append("Hello!\nName: ")
			.append(SERVICE_NAME)
			.append("\nVersion: ")
			.append(REVISION_HASH)
			.append("\nStartTime: ")
			.append(configurator.getStartTime())
			.append("\n\n--------------------\nConfiguration:\n")
			.append(configurator.toString())
			.toString();
	}
}
