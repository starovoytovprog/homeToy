package ru.starovoytov.home.toy.vk.collector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.starovoytov.home.toy.vk.exceptions.VkException;
import ru.starovoytov.home.toy.vk.log.VkCollectorLogMessageBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ru.starovoytov.home.toy.vk.collector.VkHelper.getLastPostsUrl;
import static ru.starovoytov.home.toy.vk.log.MarkersHelper.VK_GET_POST_LIST;

/**
 * Сборщик данных из ВК
 *
 * @author starovoytov
 * @since 2020.02.15
 */
public class Collector {
	private static final Logger LOGGER = LogManager.getLogger(VkHelper.class);

	/**
	 * Соответствие id стены и времени последней проверки постов
	 */
	private final transient Map<Integer, Long> times;

	/**
	 * Конструктор по умолчанию
	 *
	 * @param times начальные установки времени проверки постов
	 */
	public Collector(final Map<Integer, Long> times) {
		this.times = times;
	}

	/**
	 * Собрать записи со стен
	 *
	 * @param idList список id стен
	 * @return список записей
	 */
	public List<String> getNextUrlList(final String idList) {
		final List<String> resultList = new ArrayList<>();
		for (final String id : idList.split(";")) {
			resultList.addAll(getFromWall(id));
		}
		return resultList;
	}

	/**
	 * Получиь последние записи со стены
	 *
	 * @param stringId id стены
	 * @return последние записи
	 */
	private List<String> getFromWall(final String stringId) {
		final Integer wallId = Integer.parseInt(stringId);
		final long startTime = times.get(wallId) == null ? System.currentTimeMillis() : times.get(wallId);
		final List<String> result = new ArrayList<>();

		try {
			result.addAll(getLastPostsUrl(wallId, startTime));
			times.put(wallId, System.currentTimeMillis());
		} catch (VkException ex) {
			LOGGER.error(VK_GET_POST_LIST, () -> VkCollectorLogMessageBuilder.create()
				.addMsg("Ошибка получения списка постов")
				.build(), ex);
		}

		return result;
	}
}