package ru.starovoytov.home.toy.test.utils.undertow;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import ru.starovoytov.home.toy.test.utils.undertow.handlers.HelloHandler;

import java.util.Collection;

/**
 * Сервис undertow
 *
 * @author starovoytov
 * @since 2020.02.11
 */
public class UndertowHttpService {
	/**
	 * Инстанс undertow
	 */
	private final transient Undertow service;

	/**
	 * Конструктор http-сервиса
	 *
	 * @param port        порт
	 * @param host        хост
	 * @param descriptors коллекция дескрипторов хендлеров
	 */
	public UndertowHttpService(final int port, final String host, final Collection<HttpHandlerDescriptor> descriptors) {
		final PathHandler pathHandlers = Handlers.path();

		for (final HttpHandlerDescriptor descriptor : descriptors) {
			pathHandlers.addPrefixPath(descriptor.getPath(), descriptor.getHandler());
		}

		pathHandlers.addPrefixPath(HelloHandler.getDefaultPath(), new HelloHandler());
		service = Undertow.builder().addHttpListener(port, host, pathHandlers).build();
	}

	/**
	 * Запуск сервиса
	 */
	public void start() {
		service.start();
	}

	/**
	 * Остановка сервиса
	 */
	public void stop() {
		service.stop();
	}
}
