package ru.starovoytov.home.toy.common.libs.cache;

import org.junit.jupiter.api.Test;
import ru.starovoytov.home.toy.common.libs.exceptions.UpdateCacheException;

import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест обновляемого кэша {@link AbstractUpdatedCache}
 *
 * @author starovoytov
 * @since 2020.01.08
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor", "PMD.AvoidDuplicateLiterals"})
class UpdatedCacheTest {

	/**
	 * Не верное значение в кэше
	 */
	private static final String BAD_ENTITY = "Bad entity in cache";

	/**
	 * Превышение времени ожидания
	 */
	private static final String DO_NOT_AWAY = "Don't away";

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
	 * Тест автоматического обновления 1
	 *
	 * @throws InterruptedException прерывание
	 */
	@Test
	@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts"})
	public void autoUpdate1Test() throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(1);
		final UpdatedCache testCache = new TestCache(latch, 1);
		assertTrue(latch.await(500, TimeUnit.MILLISECONDS), DO_NOT_AWAY);
		assertEquals(1, testCache.getEntity(), BAD_ENTITY);
	}

	/**
	 * Тест автоматического обновления 2
	 *
	 * @throws InterruptedException прерывание
	 */
	@Test
	@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts"})
	public void autoUpdate2Test() throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(2);
		final UpdatedCache testCache = new TestCache(latch, 1);
		assertTrue(latch.await(500, TimeUnit.MILLISECONDS), DO_NOT_AWAY);
		assertEquals(2, testCache.getEntity(), BAD_ENTITY);
	}

	/**
	 * Тест автоматического обновления 3
	 *
	 * @throws InterruptedException прерывание
	 */
	@Test
	@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts"})
	public void autoUpdate3Test() throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(5);
		final UpdatedCache testCache = new TestCache(latch, 1);
		assertTrue(latch.await(500, TimeUnit.MILLISECONDS), DO_NOT_AWAY);
		assertEquals(5, testCache.getEntity(), BAD_ENTITY);
	}

	/**
	 * Тест автоматического обновления 4
	 *
	 * @throws InterruptedException прерывание
	 */
	@Test
	@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts"})
	public void autoUpdate4Test() throws InterruptedException {
		final CountDownLatch latch = new CountDownLatch(20);
		final UpdatedCache testCache = new TestCache(latch, 1);
		assertTrue(latch.await(500, TimeUnit.MILLISECONDS), DO_NOT_AWAY);
		assertEquals(15, testCache.getEntity(), BAD_ENTITY);
	}

	/**
	 * Кэш для тестов
	 */
	private static class TestCache extends AbstractUpdatedCache<Integer> {

		private static final long ZERO = 0L;

		/**
		 * В тесте считается количество обновлений
		 */
		private transient int updateCount;

		private final transient CountDownLatch latch;

		private TestCache() {
			super(-1, null);
			this.latch = new CountDownLatch(500);
		}

		private TestCache(final CountDownLatch latch, final long interval) {
			super(interval, null);
			this.latch = latch;
		}

		@Override
		protected void setParams(final Map params) {
		}

		@Override
		@SuppressWarnings({"PMD.AvoidLiteralsInIfCondition"})
		protected Integer getNewEntity() throws UpdateCacheException {
			if (latch != null) {
				latch.countDown();
			}
			if (latch == null || latch.getCount() != ZERO) {
				if (updateCount == 15) {
					throw new UpdateCacheException("test exception", null);
				}
				updateCount++;
			}
			return updateCount;
		}
	}
}
