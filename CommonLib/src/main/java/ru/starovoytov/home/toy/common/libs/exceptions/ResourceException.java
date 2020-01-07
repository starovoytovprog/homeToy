package ru.starovoytov.home.toy.common.libs.exceptions;

/**
 * Ошибка обращения к ресурсу
 *
 * @author starovoytov
 * @since 2020.01.07
 */
public class ResourceException extends BaseException {
	private static final long serialVersionUID = 1L;

	/**
	 * Конструктор с сообщением о ошибке и причиной
	 *
	 * @param message сообщение
	 * @param cause   причина
	 */
	public ResourceException(final String message, final Exception cause) {
		super(message, cause);
	}
}
