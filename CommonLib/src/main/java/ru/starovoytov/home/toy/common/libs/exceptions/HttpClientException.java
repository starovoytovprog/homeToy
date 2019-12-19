package ru.starovoytov.home.toy.common.libs.exceptions;

/**
 * Ошибка вызова http-клиента
 *
 * @author starovoytov
 * @since 2019.12.19
 */
public class HttpClientException extends BaseException {
	private static final long serialVersionUID = 1L;

	/**
	 * Конструктор с сообщением о ошибке и причиной
	 *
	 * @param message сообщение
	 * @param cause   причина
	 */
	public HttpClientException(final String message, final Exception cause) {
		super(message, cause);
	}
}
