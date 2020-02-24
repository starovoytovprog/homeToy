package ru.starovoytov.home.toy.telegram.exceptions;

import ru.starovoytov.home.toy.common.libs.exceptions.BaseException;

/**
 * Ошибка соединения с telegram
 *
 * @author starovoytov
 * @since 2020.02.21
 */
public class TelegramConnectionException extends BaseException {
	private static final long serialVersionUID = 1L;

	/**
	 * Конструктор с сообщением о ошибке и причиной
	 *
	 * @param message сообщение
	 * @param cause   причина
	 */
	public TelegramConnectionException(final String message, final Exception cause) {
		super(message, cause);
	}
}
