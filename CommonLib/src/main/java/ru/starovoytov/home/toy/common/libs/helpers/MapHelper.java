package ru.starovoytov.home.toy.common.libs.helpers;

import java.util.Locale;
import java.util.Map;

/**
 * Помошник для работы с мапами
 *
 * @author starovoytov
 * @since 2020.02.09
 */
public final class MapHelper {

	/**
	 * Ключ, оозначающий параметр-пароль
	 */
	private static final String PASSWORD_KEY = "password";

	/**
	 * Закрытый конструктор без параметров
	 */
	private MapHelper() {
	}

	/**
	 * Формирование строки из мапы
	 *
	 * @param map мапа
	 * @return строка
	 */
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public static String mapToString(final Map<String, String> map) {
		final StringBuilder builder = new StringBuilder();

		for (final Map.Entry<String, String> entry : map.entrySet()) {
			if (!builder.toString().isEmpty()) {
				builder.append('\n');
			}
			builder.append(entry.getKey());
			builder.append('=');
			if (entry.getKey().toLowerCase(Locale.getDefault()).contains(PASSWORD_KEY)) {
				builder.append("***");
			} else {
				builder.append(entry.getValue());
			}
		}

		return builder.toString();
	}
}
