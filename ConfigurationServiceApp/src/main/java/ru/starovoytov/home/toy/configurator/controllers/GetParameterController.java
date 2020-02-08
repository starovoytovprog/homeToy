package ru.starovoytov.home.toy.configurator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

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

	@Autowired
	@Qualifier("ParametersCache")
	private transient ParametersCache parametersCache;

	/**
	 * Получить значение параметра
	 *
	 * @param name имя параметра
	 * @return значение параметра
	 */
	@GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
	@SuppressWarnings({"PMD.DataflowAnomalyAnalysis"})
	public String getProperty(final String name) {
		String property = "";
		final Properties properties = parametersCache.getEntity();

		if (properties != null) {
			property = properties.getProperty(name);
		}

		return property;
	}
}
