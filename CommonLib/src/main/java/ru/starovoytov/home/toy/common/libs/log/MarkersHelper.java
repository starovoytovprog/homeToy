package ru.starovoytov.home.toy.common.libs.log;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

/**
 * Маркеры для логов общей библиотеки
 *
 * @author starovoytov
 * @since 2019.12.19
 */
@SuppressWarnings({"PMD.LongVariable"})
public class MarkersHelper {
	/**
	 * Маркер обновления кэша
	 */
	public static final Marker UPDATE_CACHE = MarkerManager.getMarker("UPDATE_CACHE");
}
