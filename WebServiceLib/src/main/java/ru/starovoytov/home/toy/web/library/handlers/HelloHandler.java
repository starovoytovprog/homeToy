package ru.starovoytov.home.toy.web.library.handlers;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import ru.starovoytov.home.toy.common.libs.configuration.AbstractConfigurator;

/**
 * Хендлер для отображения информации о сервисе
 *
 * @author starovoytov
 * @since 2019.12.19
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
public final class HelloHandler implements HttpHandler {

	/**
	 * Путь по умолчанию для жендлера хэлло
	 */
	private static final String DEFAULT_PATH = "/hello";

	/**
	 * Конфигуратор приложения
	 */
	private final transient AbstractConfigurator configurator;

	/**
	 * Имя сервиса
	 */
	private static final String SERVICE_NAME = AbstractConfigurator.class.getPackage().getImplementationTitle();

	/**
	 * Версия сервиса
	 */
	private static final String REVISION_HASH = AbstractConfigurator.class.getPackage().getImplementationVersion();

	/**
	 * Получить путь по умолчанию для жендлера хэлло
	 *
	 * @return Путь по умолчанию для жендлера хэлло
	 */
	public static String getDefaultPath() {
		return DEFAULT_PATH;
	}

	/**
	 * Конструктор хэндлера
	 *
	 * @param configurator конфигуратор
	 */
	public HelloHandler(final AbstractConfigurator configurator) {
		this.configurator = configurator;
	}

	@Override
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public void handleRequest(final HttpServerExchange exchange) throws Exception {
		final StringBuilder response = new StringBuilder(500);
		response.append("Hello!\nName: ")
			.append(SERVICE_NAME)
			.append("\nVersion: ")
			.append(REVISION_HASH)
			.append("\n\n--------------------\nConfiguration:\n")
			.append(configurator.toString());
		exchange.getResponseSender().send(response.toString());
	}
}
