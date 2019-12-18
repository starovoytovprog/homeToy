package ru.starovoytov.home.toy.common.libs.exceptions;

/**
 * Базовое исключение для приложения
 *
 * @author starovoytov
 * @since 2019.12.11
 */
public class BaseException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Конструктор с сообщением о ошибке
	 *
	 * @param message сообщение
	 */
	public BaseException(final String message) {
		super(message);
	}

	/**
	 * Конструктор с сообщением о ошибке и причиной
	 *
	 * @param message сообщение
	 * @param cause   причина
	 */
	public BaseException(final String message, final Exception cause) {
		super(message, cause);
	}
}
