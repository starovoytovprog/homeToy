package ru.starovoytov.home.toy.springboot.library.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.starovoytov.home.toy.common.libs.configuration.AbstractConfigurator;
import ru.starovoytov.home.toy.springboot.library.controllers.log.SpringBootLibLogMessageBuilder;

import java.io.IOException;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import static ru.starovoytov.home.toy.springboot.library.controllers.log.MarkersHelper.HELLO_CONTROLLER;

/**
 * Контроллер для отображения информации о сервисе
 *
 * @author starovoytov
 * @since 2020.02.02
 */
@RestController("/hello")
@RequestMapping("hello")
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
public class HelloController {
	private static final Logger LOGGER = LogManager.getLogger(HelloController.class);

	@Autowired
	@Qualifier("Configurator")
	private transient AbstractConfigurator configurator;

	@GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
	public String helloGradle() {
		final ManifestHelper manifestHelper = new ManifestHelper(configurator.getClass());

		return new StringBuilder(500).append("Hello!\nName: ")
			.append(manifestHelper.getImplementationTitle())
			.append("\nVersion: ")
			.append(manifestHelper.getImplementationVersion())
			.append("\nStartTime: ")
			.append(configurator.getStartTime())
			.append("\n\n--------------------\n")
			.append(configurator.toString())
			.toString();
	}

	/**
	 * Помошник для работы с манифестом
	 */
	private static class ManifestHelper {
		private final transient Manifest manifest;

		/**
		 * Конструктор
		 *
		 * @param clazz класс, в jar которого необходимо выполнять поиск манифеста
		 */
		private ManifestHelper(final Class clazz) {
			Manifest manifest = null;
			try {
				final Object content = clazz.getProtectionDomain().getCodeSource().getLocation().getContent();
				if (content instanceof JarFile) {
					manifest = ((JarFile) content).getManifest();
				}
			} catch (IOException ex) {
				LOGGER.error(HELLO_CONTROLLER, () -> SpringBootLibLogMessageBuilder.create()
					.addMsg("Ошибка получения манифеста")
					.build(), ex);
			}

			this.manifest = manifest;
		}

		/**
		 * Получить версию из манифеста
		 *
		 * @return версия из манифеста
		 */
		@SuppressWarnings({"PMD.LawOfDemeter", "PMD.DataflowAnomalyAnalysis"})
		public String getImplementationVersion() {
			String result = null;
			if (manifest != null) {
				result = manifest.getMainAttributes().getValue("Implementation-Version");
			}
			return result;
		}

		/**
		 * Получить заголовок из манифеста
		 *
		 * @return заголовок из манифеста
		 */
		@SuppressWarnings({"PMD.LawOfDemeter", "PMD.DataflowAnomalyAnalysis"})
		public String getImplementationTitle() {
			String result = null;
			if (manifest != null) {
				result = manifest.getMainAttributes().getValue("Implementation-Title");
			}
			return result;
		}
	}
}
