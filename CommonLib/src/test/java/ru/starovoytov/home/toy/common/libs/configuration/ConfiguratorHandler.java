package ru.starovoytov.home.toy.common.libs.configuration;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

/**
 * Хендлер-заглушка для конфигурации
 *
 * @author starovoytov
 * @since 2020.02.11
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
public class ConfiguratorHandler implements HttpHandler {
	@Override
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public void handleRequest(final HttpServerExchange exchange) {
		exchange.getResponseSender().send(exchange.getQueryParameters().get("name") + " value");
	}
}
