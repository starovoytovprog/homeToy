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
	 * Не верное значение в кэше
	 */
	private static final String BAD_ENTITY = "Bad entity in cache";

	/**
	 * Тест ручного обновления 1
	 */
	@Test
	public void handleUpdate1Test() {
		final TestCache testCache = new TestCache();
		assertEquals(1, testCache.getEntity(), BAD_ENTITY);
	}

	/**
	 * Тест ручного обновления 2
	 */
	@Test
	public void handleUpdate2Test() {
		final TestCache testCache = new TestCache();
		testCache.update();
		assertEquals(2, testCache.getEntity(), BAD_ENTITY);
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
		assertEquals(11, testCache.getEntity(), BAD_ENTITY);
	}

	/**
	 * Тест ручного обновления 5
	 */
	@Test
	public void handleUpdate5Test() {
		final UpdatedCache testCache = new TestCache();
		assertEquals(1, testCache.getEntity(), BAD_ENTITY);
	}

	/**
	 * Тест ручного обновления 6
	 */
	@Test
	public void handleUpdate6Test() {
		final UpdatedCache testCache = new TestCache();
		testCache.update();
		assertEquals(2, testCache.getEntity(), BAD_ENTITY);
	}

	/**
	 * Тест ручного обновления 7
	 */
	@Test
	public void handleUpdate7Test() {
		final UpdatedCache testCache = new TestCache();
		assertEquals("1", testCache.displayEntity(), BAD_ENTITY);
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
