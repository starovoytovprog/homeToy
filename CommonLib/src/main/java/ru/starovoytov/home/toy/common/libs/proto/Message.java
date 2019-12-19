package ru.starovoytov.home.toy.common.libs.proto;

/**
 * Сообщение
 *
 * @author starovoytov
 * @since 2019.12.19
 */
public class Message<B extends AbstractBody> {
	/**
	 * Заголовок сообщения
	 */
	private final Head head;
	/**
	 * Тело сообщения
	 */
	private final B body;

	/**
	 * Конструктор сообщения
	 *
	 * @param head заголовок
	 * @param body тело
	 */
	public Message(Head head, B body) {
		this.head = head;
		this.body = body;
	}

	/**
	 * Получить заголовок сообщения
	 *
	 * @return заголовок сообщения
	 */
	public Head getHead() {
		return head;
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
