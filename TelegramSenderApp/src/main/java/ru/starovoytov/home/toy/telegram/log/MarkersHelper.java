package ru.starovoytov.home.toy.telegram.log;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

/**
 * Маркеры для логов отправителя телеграм
 *
 * @author starovoytov
 * @since 2020.02.20
 */
@SuppressWarnings({"PMD.LongVariable"})
public class MarkersHelper {
	/**
	 * Маркер отправки сообщения
	 */
	public static final Marker TELEGRAM_SEND_MESSAGE = MarkerManager.getMarker("TELEGRAM_SEND_MESSAGE");

	/**
	 * Маркер соединения
	 */
	public static final Marker TELEGRAM_CONNECT = MarkerManager.getMarker("TELEGRAM_CONNECT");
}
