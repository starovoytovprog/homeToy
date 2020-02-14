package ru.starovoytov.home.toy.common.libs.exceptions;

/**
 * Исключение при обращении к RabbitMq
 *
 * @author starovoytov
 * @since 2020.02.14
 */
public class RabbitMqException extends BaseException {
	private static final long serialVersionUID = 1L;

	/**
	 * Конструктор с сообщением о ошибке и причиной
	 *
	 * @param message сообщение
	 * @param cause   причина
	 */
	public RabbitMqException(final String message, final Exception cause) {
		super(message, cause);
	}
}
