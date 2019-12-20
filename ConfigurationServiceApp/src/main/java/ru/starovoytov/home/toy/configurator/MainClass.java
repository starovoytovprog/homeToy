package ru.starovoytov.home.toy.configurator;

import ru.starovoytov.home.toy.configurator.configuration.Configurator;
import ru.starovoytov.home.toy.web.library.UndertowHttpService;

import java.util.ArrayList;

/**
 * Стартовый класс сервиса конфигурации
 *
 * @author starovoytov
 * @since 2019.12.19
 */
public final class MainClass {

	/**
	 * Инстанс сервиса
	 */
	private final transient UndertowHttpService service;

	/**
	 * Конструктор по умолчанию
	 */
	private MainClass() {
		service = new UndertowHttpService(Configurator.getInstance().getPort(), Configurator.getInstance()
			.getHost(), new ArrayList<>());
	}

	/**
	 * Запуск сервиса
	 */
	public void start() {
		service.start();
	}

	/**
	 * Точка запуска
	 *
	 * @param args аргументы командной строки
	 */
	public static void main(final String[] args) {
		final MainClass mainClass = new MainClass();
		mainClass.start();
	}
}
