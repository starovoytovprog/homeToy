package ru.starovoytov.home.toy.common.libs.cache;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тест обновляемого кэша {@link AbstractUpdatedCache}
 *
 * @author starovoytov
 * @since 2020.01.08
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
class UpdatedCacheTest {
	/**
	 * Тест ручного обновления 1
	 */
	@Test
	public void handleUpdate1Test() {
		final TestCache testCache = new TestCache();
		assertEquals(1, testCache.getEntity(), "Bad entity in cache");
	}

	/**
	 * Тест ручного обновления 2
	 */
	@Test
	public void handleUpdate2Test() {
		final TestCache testCache = new TestCache();
		testCache.update();
		assertEquals(1, testCache.getEntity(), "Bad entity in cache");
	}

	/**
	 * Тест ручного обновления 3
	 */
	@Test
	public void handleUpdate3Test() {
		final TestCache testCache = new TestCache();

		for (int i = 0; i < 10; i++) {
			testCache.update();
		}
		assertEquals(10, testCache.getEntity(), "Bad entity in cache");
	}

	/**
	 * Кэш для тестов
	 */
	private static class TestCache extends AbstractUpdatedCache<Integer> {

		/**
		 * В тесте считается количество обновлений
		 */
		private transient int updateCount;

		private TestCache() {
			super(-1, null);
			updateCount = 0;
		}

		@Override
		protected void setParams(final Map params) {
		}

		@Override
		protected Integer getNewEntity() {
			updateCount++;
			return updateCount;
		}
	}
}
