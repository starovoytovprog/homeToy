package ru.starovoytov.home.toy.common.libs.generators;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Хэлпер для генерации идентификаторов операций
 *
 * @author starovoytov
 * @since 2019.12.10
 */
public final class UidGeneratorHelper {

	private static final AtomicLong TIME_MARKER = new AtomicLong(System.currentTimeMillis());

	/**
	 * Закрытый конструктор без параметров
	 */
	private UidGeneratorHelper() {
	}

	/**
	 * Сгенерировать идентификатор операции
	 *
	 * @return идентификатор операции
	 */
	public static String getNewUid() {
		long rand = (long) (Math.random() * 1_000_000_000);
		rand *= TIME_MARKER.incrementAndGet() + System.currentTimeMillis();
		return Long.toString(rand);
	}
}
