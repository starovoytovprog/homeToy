package ru.starovoytov.home.toy.common.libs.proto;

import java.util.Objects;

/**
 * Заголовок сообщения
 *
 * @author starovoytov
 * @since 2019.12.19
 */
public class Head {
	/**
	 * Версия протокола
	 */
	private String version;
	/**
	 * uid операции
	 */
	private String uid;
	/**
	 * Отправитель сообщения
	 */
	private String sender;
	/**
	 * Получатель сообщения
	 */
	private String receiver;

	/**
	 * Получить версию протокола
	 *
	 * @return версия протокола
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Задать версию протокола
	 *
	 * @param version версия протокола
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * Получить uid операции
	 *
	 * @return uid операции
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * Установить uid операции
	 *
	 * @param uid uid операции
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * Получить отправителя
	 *
	 * @return отправитель
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * Установить отправителя
	 *
	 * @param sender отправитель
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * Получить получателя
	 *
	 * @return получатель
	 */
	public String getReceiver() {
		return receiver;
	}

	/**
	 * Установить получателя
	 *
	 * @param receiver получатель
	 */
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Head)) return false;
		Head head = (Head) o;
		return Objects.equals(getVersion(), head.getVersion()) && Objects.equals(getUid(), head.getUid()) && Objects.equals(getSender(), head
			.getSender()) && Objects.equals(getReceiver(), head.getReceiver());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getVersion(), getUid(), getSender(), getReceiver());
	}

	@Override
	public String toString() {
		return "Head{" + "version='" + version + '\'' + ", uid='" + uid + '\'' + ", sender='" + sender + '\'' + ", receiver='" + receiver + '\'' + '}';
	}
}
