package ru.starovoytov.home.toy.vk.collector;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест сборщика сообщений
 *
 * @author starovoytov
 * @since 2020.02.15
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
class CollectorTest {

	/**
	 * Тест сбора записей со стен
	 */
	@Test
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public void testCollect() {
		final TestConfigurator configurator = new TestConfigurator();
		final Map<Integer, Long> map = new ConcurrentHashMap<>();
		map.put(configurator.getTestOwnerId(), 0L);
		map.put(Integer.MAX_VALUE, 0L);
		final Collector collector = new Collector(map);
		final List<String> urlList = collector.getNextUrlList(Integer.MAX_VALUE + ";" + configurator.getTestOwnerId());
		assertTrue(urlList.contains(configurator.getTestAddress()), "Message not found");
	}
}
