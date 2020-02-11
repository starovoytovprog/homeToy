package ru.starovoytov.home.toy.springboot.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.starovoytov.home.toy.common.libs.cache.UpdatedCache;

import java.util.Map;

/**
 * Контроллер для получения информации о кэше
 *
 * @author starovoytov
 * @since 2020.02.09
 */
@RestController("/caches/get")
@RequestMapping("caches/get")
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
public class GetCacheController {
	@Autowired
	@Qualifier("Caches")
	private transient Map<String, UpdatedCache> caches;

	/**
	 * Получить значение кэша
	 *
	 * @param name имя кэша
	 * @return значение кэша
	 */
	@GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
	@SuppressWarnings({"PMD.DataflowAnomalyAnalysis"})
	public String getProperty(final String name) {
		final UpdatedCache cache = caches.get(name);
		String cacheText = "";

		if (cache != null) {
			cacheText = cache.displayEntity();
		}

		return cacheText;
	}
}
