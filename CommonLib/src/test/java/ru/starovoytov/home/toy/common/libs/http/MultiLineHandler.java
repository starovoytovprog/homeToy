package ru.starovoytov.home.toy.common.libs.http;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;

/**
 * Хендлер, возвращающий многострочный текст
 *
 * @author starovoytov
 * @since 2020.02.11
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
public class MultiLineHandler implements HttpHandler {
	@Override
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public void handleRequest(final HttpServerExchange exchange) {
		exchange.getResponseSender().send("Hello!\nIt's multi-line handler.\nAnd next line.");
	}
}
