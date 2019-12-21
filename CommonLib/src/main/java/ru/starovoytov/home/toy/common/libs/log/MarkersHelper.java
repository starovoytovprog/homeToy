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
	 * Маркер http-клиента
	 */
	public static final Marker HTTP_CLIENT = MarkerManager.getMarker("HTTP_CLIENT");

	/**
	 * Маркер отправка запроса http-клиентом
	 */
	public static final Marker HTTP_CLIENT_REQUEST = MarkerManager.getMarker("HTTP_CLIENT_REQUEST");

	/**
	 * Маркер отправка запроса http-клиентом
	 */
	public static final Marker HTTP_CLIENT_RESPONSE = MarkerManager.getMarker("HTTP_CLIENT_RESPONSE");
}
