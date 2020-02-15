package ru.starovoytov.home.toy.vk.log;

import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

/**
 * Маркеры для логов сервиса сбора сообщений из ВК
 *
 * @author starovoytov
 * @since 2020.02.15
 */
public class MarkersHelper {
	/**
	 * Маркер получения списка постов со стены
	 */
	public static final Marker VK_GET_POST_LIST = MarkerManager.getMarker("VK_GET_POST_LIST");
}
