package ru.starovoytov.home.toy.web.library.log;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

/**
 * Маркеры для логов библиотеки веб-сервисов
 *
 * @author starovoytov
 * @since 2019.12.19
 */
public class MarkersHelper {
	/**
	 * Маркер инициализации сервиса
	 */
	public static final Marker SERVICE = MarkerManager.getMarker("SERVICE");
}
