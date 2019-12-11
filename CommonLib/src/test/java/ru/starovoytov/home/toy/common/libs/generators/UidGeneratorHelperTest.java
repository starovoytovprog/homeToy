package ru.starovoytov.home.toy.common.libs.generators;

import org.junit.jupiter.api.Test;
import ru.starovoytov.home.toy.common.libs.exceptions.UidGenerationException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.starovoytov.home.toy.common.libs.generators.UidGeneratorHelper.getNewUid;

/**
 * Тест хэлпера для генерации идентификаторов операции {@link UidGeneratorHelper}
 *
 * @author starovoytov
 * @since 2019.12.10
 */
final class UidGeneratorHelperTest {
	private static final int GENERATE_COUNT = 1000;
	private static final int THREAD_COUNT = 10;

	private static final Object MONITOR = new Object();

	public UidGeneratorHelperTest() {
	}

	/**
	 * Тест генерации uid
	 *
	 * @throws InterruptedException прерывание
	 */
	@SuppressWarnings({"PMD.AvoidInstantiatingObjectsInLoops", "PMD.DoNotUseThreads", "PMD.JUnitTestContainsTooManyAsserts"})
	@Test
	public void testGetUid() throws InterruptedException {
		final List<UidGenerateThread> threads = new ArrayList<>(THREAD_COUNT);

		for (int i = 0; i < THREAD_COUNT; i++) {
			threads.add(new UidGenerateThread());
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
	 * Класс-поток для генерации uid
	 */
	@SuppressWarnings({"PMD.DoNotUseThreads"})
	private static final class UidGenerateThread extends Thread {

		/**
		 * Ссылка на коллекцию для сохранения идентификаторов
		 */
		private final Set<String> uidSet;

		/**
		 * Конструктор потока
		 */
		private UidGenerateThread() {
			super();
			this.uidSet = new HashSet<>();
		}

		/**
		 * Заполнение коллекции идентификаторов
		 */
		@Override
		@SuppressWarnings({"PMD.AvoidPrintStackTrace"})
		public void run() {
			for (int i = 0; i < GENERATE_COUNT; i++) {
				synchronized (MONITOR) {
					try {
						uidSet.add(getNewUid());
					} catch (UidGenerationException e) {
						e.printStackTrace();
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
	}
}
