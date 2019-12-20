package ru.starovoytov.home.toy.web.library;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.starovoytov.home.toy.web.library.handlers.HelloHandler;
import ru.starovoytov.home.toy.web.library.log.MarkersHelper;
import ru.starovoytov.home.toy.web.library.log.WebServiceLogMessageBuilder;

import java.util.Collection;

/**
 * Сервис undertow
 *
 * @author starovoytov
 * @since 2019.12.19
 */
public class UndertowHttpService {
	private static final Logger LOGGER = LogManager.getLogger(UndertowHttpService.class);
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

		LOGGER.info(MarkersHelper.SERVICE, () -> WebServiceLogMessageBuilder.create()
			.addMsg("Service initialized")
			.build());
	}

	/**
	 * Запуск сервиса
	 */
	public void start() {
		service.start();
		LOGGER.info(MarkersHelper.SERVICE, () -> WebServiceLogMessageBuilder.create()
			.addMsg("Service started")
			.build());
	}

	/**
	 * Остановка сервиса
	 */
	public void stop() {
		service.stop();
		LOGGER.info(MarkersHelper.SERVICE, () -> WebServiceLogMessageBuilder.create()
			.addMsg("Service stopped")
			.build());
	}
}
