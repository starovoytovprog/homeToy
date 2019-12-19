package ru.starovoytov.home.toy.web.library.handlers;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

/**
 * Хендлер для отображения информации о сервисе
 *
 * @author starovoytov
 * @since 2019.12.19
 */
public class HelloHandler implements HttpHandler {
	/**
	 * Путь по умолчанию для жендлера хэлло
	 */
	private static final String DEFAULT_PATH = "/hello";

	/**
	 * Получить путь по умолчанию для жендлера хэлло
	 *
	 * @return Путь по умолчанию для жендлера хэлло
	 */
	public static String getDefaultPath() {
		return DEFAULT_PATH;
	}

	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		exchange.getResponseSender().send("Hello!");
	}
}
