package ru.starovoytov.home.toy.springboot.library.controllers;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.starovoytov.home.toy.common.libs.cache.UpdatedCache;

import java.util.Map;

/**
 * Контроллер обновления кэшей
 *
 * @author starovoytov
 * @since 2020.02.09
 */
@RestController("/caches/update")
@RequestMapping("caches/update")
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
public class UpdateCacheController {
	@Autowired
	@Qualifier("Caches")
	private transient Map<String, UpdatedCache> caches;

	/**
	 * Обновить кэши
	 *
	 * @return отчёт обновления кэшей
	 */
	@GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
	@SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.LawOfDemeter"})
	@SuppressFBWarnings({"WMI_WRONG_MAP_ITERATOR"})
	public String getProperty() {
		final StringBuilder builder = new StringBuilder(50).append("Update caches\n\n");

		for (final String key : caches.keySet()) {
			caches.get(key).update();
			builder.append(key).append(": updated\n");
		}

		return builder.toString();
	}
}
