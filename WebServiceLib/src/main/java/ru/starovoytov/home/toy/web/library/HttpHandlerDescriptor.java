package ru.starovoytov.home.toy.web.library;

import io.undertow.server.HttpHandler;

/**
 * Дескриптор хендлера
 *
 * @author starovoytov
 * @since 2019.12.19
 */
public class HttpHandlerDescriptor {
	/**
	 * Путь к хендлеру
	 */
	private final String path;
	/**
	 * Хендлер
	 */
	private final HttpHandler handler;

	/**
	 * Конструктор дескриптора
	 *
	 * @param path    путь к хендлеру
	 * @param handler хендлер
	 */
	public HttpHandlerDescriptor(final String path, final HttpHandler handler) {
		this.path = path;
		this.handler = handler;
	}

	/**
	 * Получить путь к хендлеру
	 *
	 * @return путь к хэндлеру
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Получить хендлер
	 *
	 * @return хендлер
	 */
	public HttpHandler getHandler() {
		return handler;
	}
}
