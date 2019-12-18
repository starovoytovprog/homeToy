package ru.starovoytov.home.toy.common.libs.exceptions;

/**
 * Ошибка при генерации uid
 *
 * @author starovoytov
 * @since 2019.12.11
 */
public class UidGenerationException extends BaseException {

	private static final long serialVersionUID = 1L;

	/**
	 * Конструктор с сообщением о ошибке и причиной
	 *
	 * @param message сообщение
	 * @param cause   причина
	 */
	public UidGenerationException(final String message, final Exception cause) {
		super(message, cause);
	}
}
