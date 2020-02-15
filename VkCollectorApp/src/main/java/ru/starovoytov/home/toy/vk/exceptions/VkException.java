package ru.starovoytov.home.toy.vk.exceptions;

import ru.starovoytov.home.toy.common.libs.exceptions.BaseException;

/**
 * Исключение при обращении к ВК
 *
 * @author starovoytov
 * @since 2020.02.15
 */
public class VkException extends BaseException {
	private static final long serialVersionUID = 1L;

	/**
	 * Конструктор с сообщением о ошибке и причиной
	 *
	 * @param message сообщение
	 * @param cause   причина
	 */
	public VkException(final String message, final Exception cause) {
		super(message, cause);
	}
}
