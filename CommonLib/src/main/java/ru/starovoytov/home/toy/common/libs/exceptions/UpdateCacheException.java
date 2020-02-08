package ru.starovoytov.home.toy.common.libs.exceptions;

/**
 * Ошибка обновления кэша
 *
 * @author starovoytov
 * @since 2020.02.08
 */
public class UpdateCacheException extends BaseException {
	private static final long serialVersionUID = 1L;

	/**
	 * Конструктор с сообщением о ошибке и причиной
	 *
	 * @param message сообщение
	 * @param cause   причина
	 */
	public UpdateCacheException(final String message, final Exception cause) {
		super(message, cause);
	}
}
