package ru.starovoytov.home.toy.common.libs.proto;

/**
 * Сообщение
 *
 * @author starovoytov
 * @since 2019.12.19
 */
public class Message<B extends BaseBody> {
	/**
	 * Заголовок сообщения
	 */
	private final MessageHead messageHead;
	/**
	 * Тело сообщения
	 */
	private final B body;

	/**
	 * Конструктор сообщения
	 *
	 * @param messageHead заголовок
	 * @param body тело
	 */
	public Message(final MessageHead messageHead, final B body) {
		this.messageHead = messageHead;
		this.body = body;
	}

	/**
	 * Получить заголовок сообщения
	 *
	 * @return заголовок сообщения
	 */
	public MessageHead getMessageHead() {
		return messageHead;
	}

	/**
	 * Получить тело сообщения
	 *
	 * @return тело сообщения
	 */
	public B getBody() {
		return body;
	}
}
