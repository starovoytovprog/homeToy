package ru.starovoytov.home.toy.common.libs.generators;

import org.junit.jupiter.api.Test;
import ru.starovoytov.home.toy.common.libs.exceptions.UidGenerationException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.starovoytov.home.toy.common.libs.generators.UidGeneratorHelper.getNewUid;
import static ru.starovoytov.home.toy.common.libs.generators.UidGeneratorHelper.hashUid;

/**
 * Тест хэлпера для генерации идентификаторов операции {@link UidGeneratorHelper}
 *
 * @author starovoytov
 * @since 2019.12.10
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
final class UidGeneratorHelperTest {
	private static final int GENERATE_COUNT = 1000;
	private static final int THREAD_COUNT = 10;

	private static final Object MONITOR = new Object();

	/**
	 * Тестирование исключения при не верном алгоритме
	 */
	@Test
	@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.LawOfDemeter"})
	public void testBadAlgorithm() {
		final Exception exception = assertThrows(UidGenerationException.class, () -> hashUid("test uid", "bad algorithm"));
		assertEquals("Bad algorithm", exception.getMessage(), "Bad exception message");
	}

	/**
	 * Тест генерации uid
	 *
	 * @throws InterruptedException прерывание
	 */
	@SuppressWarnings(
		{"PMD.AvoidInstantiatingObjectsInLoops", "PMD.DoNotUseThreads", "PMD.JUnitTestContainsTooManyAsserts"})
	@Test
	public void testGetUid() throws InterruptedException {
		final List<UidGenerateThread> threads = new ArrayList<>(THREAD_COUNT);

		for (int i = 0; i < THREAD_COUNT; i++) {
			threads.add(new UidGenerateThread("SHA-1"));
		}

		for (final UidGenerateThread thread : threads) {
			thread.start();
		}

		for (final UidGenerateThread thread : threads) {
			thread.join();
		}

		final Set<String> uidSet = new HashSet<>();

		for (final UidGenerateThread thread : threads) {
			uidSet.addAll(thread.getUidSet());
		}

		assertEquals(GENERATE_COUNT * THREAD_COUNT, uidSet.size(), "Error in test generation unique uid");

		for (final String uid : uidSet) {
			assertEquals(56, uid.length(), "Uid have bad length");
		}
	}

	/**
	 * Тест генерации uid с ошибкой
	 *
	 * @throws InterruptedException прерывание
	 */
	@SuppressWarnings(
		{"PMD.AvoidInstantiatingObjectsInLoops", "PMD.DoNotUseThreads", "PMD.JUnitTestContainsTooManyAsserts"})
	@Test
	public void testErrorGetUid() throws InterruptedException {
		final List<UidGenerateThread> threads = new ArrayList<>(THREAD_COUNT);

		for (int i = 0; i < THREAD_COUNT; i++) {
			threads.add(new UidGenerateThread("bad algorithm"));
		}

		for (final UidGenerateThread thread : threads) {
			thread.start();
		}

		for (final UidGenerateThread thread : threads) {
			thread.join();
		}

		int errorCount = 0;

		for (final UidGenerateThread thread : threads) {
			errorCount += thread.getErrorCount();
		}

		assertEquals(GENERATE_COUNT * THREAD_COUNT, errorCount, "Bad error count");
	}

	/**
	 * Класс-поток для генерации uid
	 */
	@SuppressWarnings({"PMD.DoNotUseThreads"})
	private static final class UidGenerateThread extends Thread {

		/**
		 * Ссылка на коллекцию для сохранения идентификаторов
		 */
		private final Set<String> uidSet;

		/**
		 * Алгоритм хеширования
		 */
		private final transient String algorithm;

		/**
		 * Количество ошибок
		 */
		private transient int errorCount;

		/**
		 * Конструктор потока
		 *
		 * @param algorithm алгоритм
		 */
		private UidGenerateThread(final String algorithm) {
			super();
			this.uidSet = new HashSet<>();
			this.algorithm = algorithm;
		}

		/**
		 * Заполнение коллекции идентификаторов
		 */
		@Override
		public void run() {
			for (int i = 0; i < GENERATE_COUNT; i++) {
				synchronized (MONITOR) {
					try {
						uidSet.add(getNewUid());
						hashUid("uid", algorithm);
					} catch (UidGenerationException e) {
						errorCount++;
					}
				}
			}
		}

		/**
		 * Получить коллекцию сгенерированных идентификаторов
		 *
		 * @return Коллекция сгенерированных идентификаторов
		 */
		public Set<String> getUidSet() {
			return uidSet;
		}

		/**
		 * Получить количество ошибок
		 *
		 * @return количество ошибок
		 */
		public int getErrorCount() {
			return errorCount;
		}
	}
}
