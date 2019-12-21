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
	 * Инстанс главного класса
	 */
	private static final MainClass INSTANCE = new MainClass();

	/**
	 * Инстанс сервиса
	 */
	private final transient UndertowHttpService service;

	/**
	 * Признак запуска сервиса
	 */
	private transient boolean started;

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
		synchronized (INSTANCE) {
			if (!started) {
				service.start();
				started = true;
			}
		}
	}

	/**
	 * Остановка сервиса
	 */
	public void stop() {
		synchronized (INSTANCE) {
			if (started) {
				service.stop();
				started = false;
			}
		}
	}

	/**
	 * Точка запуска
	 *
	 * @param args аргументы командной строки
	 */
	public static void main(final String[] args) {
		INSTANCE.start();
	}

	/**
	 * Получить ссылку на главный класс
	 *
	 * @return ссылка на главный класс
	 */
	public static MainClass getInstance() {
		return INSTANCE;
	}
}
