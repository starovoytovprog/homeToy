package ru.starovoytov.home.toy.common.libs.proto;

import java.util.Objects;

/**
 * Заголовок сообщения
 *
 * @author starovoytov
 * @since 2019.12.19
 */
@SuppressWarnings({"PMD.DataClass"})
public final class MessageHead {

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
	 * Конструктор по умолчанию
	 */
	public MessageHead() {
	}

	/**
	 * Конструктор с параметрами
	 *
	 * @param version  версия протокола
	 * @param uid      uid операции
	 * @param sender   отправитель сообщения
	 * @param receiver получатель сообщения
	 */
	public MessageHead(final String version, final String uid, final String sender, final String receiver) {
		this.version = version;
		this.uid = uid;
		this.sender = sender;
		this.receiver = receiver;
	}

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
	public void setVersion(final String version) {
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
	public void setUid(final String uid) {
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
	public void setSender(final String sender) {
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
	public void setReceiver(final String receiver) {
		this.receiver = receiver;
	}

	@Override
	@SuppressWarnings({"PMD.OnlyOneReturn", "PMD.LawOfDemeter"})
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MessageHead)) {
			return false;
		}
		final MessageHead messageHead = (MessageHead) obj;
		return Objects.equals(getVersion(), messageHead.getVersion()) && Objects.equals(getUid(), messageHead.getUid()) && Objects
			.equals(getSender(), messageHead.getSender()) && Objects.equals(getReceiver(), messageHead.getReceiver());
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
