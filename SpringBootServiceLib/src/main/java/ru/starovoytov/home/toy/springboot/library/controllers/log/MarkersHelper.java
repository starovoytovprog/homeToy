package ru.starovoytov.home.toy.springboot.library.controllers.log;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

/**
 * Маркеры для логов библиотеки spring boot
 *
 * @author starovoytov
 * @since 2020.02.04
 */
public class MarkersHelper {
	/**
	 * Маркер hello контроллера
	 */
	public static final Marker HELLO_CONTROLLER = MarkerManager.getMarker("HELLO_CONTROLLER");
}
