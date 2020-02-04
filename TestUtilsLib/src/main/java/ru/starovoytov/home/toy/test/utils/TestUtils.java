package ru.starovoytov.home.toy.test.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.ServerSocket;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Map;
import java.util.Random;

/**
 * Утилиты для тестов
 *
 * @author starovoytov
 * @since 2019.12.20
 */
public final class TestUtils {

	/**
	 * Генератор случайных чисел для тестов
	 */
	private static final Random RANDOM = new Random();

	/**
	 * Закрытый конструктор
	 */
	private TestUtils() {
	}

	/**
	 * Поиск свободного порта
	 *
	 * @return результат поиска
	 */
	public static int getFreePort() {
		final int startPort = Math.abs(RANDOM.nextInt() % (40_000)) + 1025;
		return getFreePort(startPort);
	}

	/**
	 * Поиск свободного порта
	 *
	 * @param startPort начальное значение поиска
	 * @return результат поиска
	 */
	@SuppressWarnings({"PMD.DataflowAnomalyAnalysis", "PMD.AvoidInstantiatingObjectsInLoops"})
	public static int getFreePort(final int startPort) {
		int startPortLocal = startPort;
		while (true) {
			try {
				if (startPortLocal < 1024 || startPortLocal > 49_151) {
					throw new IllegalArgumentException("Получено неверное значение порта");
				}

				try (ServerSocket sock = new ServerSocket(startPortLocal)) {
					return startPortLocal;
				}
			} catch (IOException e) {
				startPortLocal++;
			}
		}
	}

	/**
	 * Установить значение переменной окружения
	 *
	 * @param name  имя переменной
	 * @param value значение переменной
	 */
	@SuppressWarnings({"unchecked", "PMD.AvoidPrintStackTrace"})
	public static void setEnv(final String name, final String value) {
		AccessController.doPrivileged((PrivilegedAction) () -> {
			try {
				final Class unmodifiableMap = Class.forName("java.util.Collections$UnmodifiableMap");
				final Field field = unmodifiableMap.getDeclaredField("m");
				field.setAccessible(true);
				final Object obj = field.get(System.getenv());
				((Map<String, String>) obj).put(name, value);
			} catch (ClassNotFoundException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
				ex.printStackTrace();
			}
			return null;
		});
	}
}
