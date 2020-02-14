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

	/**
	 * Маркер генерирования uid для обращения в конфигуратор
	 */
	public static final Marker CONF_SERVICE_GENERATE_UID = MarkerManager.getMarker("CONF_SERVICE_GENERATE_UID");

	/**
	 * Маркер запросов в сервис
	 */
	public static final Marker SERVICE_REQUEST = MarkerManager.getMarker("SERVICE_REQUEST");

	/**
	 * Маркер обращения в RabbitMq
	 */
	public static final Marker RABBIT_MQ = MarkerManager.getMarker("RABBIT_MQ");
}
