package ru.starovoytov.home.toy.springboot.library.controllers;

import ru.starovoytov.home.toy.common.libs.configuration.AbstractConfigurator;

/**
 * Тестовый конфигуратор
 *
 * @author starovoytov
 * @since 2020.02.02
 */
public final class Configurator extends AbstractConfigurator {

	private static final Configurator INSTANCE;

	private Configurator() {
		super();
	}

	static {
		INSTANCE = new Configurator();
	}

	public static Configurator getInstance() {
		return INSTANCE;
	}

	@Override
	protected void fillDefaultParameters() {
	}
}
