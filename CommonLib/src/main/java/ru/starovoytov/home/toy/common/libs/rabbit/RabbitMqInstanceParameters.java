package ru.starovoytov.home.toy.common.libs.rabbit;

import java.util.Objects;

/**
 * Параметры подключения к RabbitMq
 *
 * @author starovoytov
 * @since 2020.02.13
 */
public class RabbitMqInstanceParameters {
	private final String host;
	private final String login;
	private final String password;

	/**
	 * Конструктор
	 *
	 * @param host     хост инстанса
	 * @param login    логин
	 * @param password пароль
	 */
	public RabbitMqInstanceParameters(final String host, final String login, final String password) {
		this.host = host;
		this.login = login;
		this.password = password;
	}

	/**
	 * Получить хост инстанса
	 *
	 * @return хост инстанса
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Получить логин
	 *
	 * @return логин
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Получить пароль
	 *
	 * @return пароль
	 */
	public String getPassword() {
		return password;
	}

	@Override
	@SuppressWarnings({"PMD.LawOfDemeter", "PMD.OnlyOneReturn"})
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof RabbitMqInstanceParameters)) {
			return false;
		}
		final RabbitMqInstanceParameters that = (RabbitMqInstanceParameters) obj;
		return Objects.equals(getHost(), that.getHost()) && Objects.equals(getLogin(), that.getLogin()) && Objects.equals(getPassword(), that
			.getPassword());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getHost(), getLogin(), getPassword());
	}
}
