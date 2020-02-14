package ru.starovoytov.home.toy.common.libs.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.starovoytov.home.toy.common.libs.exceptions.RabbitMqException;
import ru.starovoytov.home.toy.common.libs.log.CommonLogMessageBuilder;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.concurrent.TimeoutException;

import static ru.starovoytov.home.toy.common.libs.log.MarkersHelper.RABBIT_MQ;

/**
 * Помошник для работы с RabbitMQ
 *
 * @author starovoytov
 * @since 2020.02.12
 */
public final class RabbitMqHelper {
	private static final Logger LOGGER = LogManager.getLogger(RabbitMqHelper.class);

	/**
	 * Закрытый конструктор
	 */
	private RabbitMqHelper() {
	}

	/**
	 * Отправка сообщений в Rabbit
	 *
	 * @param instance  описание инстанса рэббита
	 * @param queueName имя очереди
	 * @param messages  список сообщений
	 * @throws RabbitMqException ошибка отправки сообщений
	 */
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public static void simpleSendStringMessages(final RabbitMqInstanceParameters instance, final String queueName,
		final Collection<String> messages) throws RabbitMqException {
		try (Connection connection = ConnectionFactoryMapper.getInstance().getConnection(instance);
		     Channel channel = connection.createChannel()) {
			channel.queueDeclare(queueName, false, false, false, null);
			for (final String message : messages) {
				channel.basicPublish("", queueName, null, message.getBytes(Charset.defaultCharset()));
			}
		} catch (IOException | TimeoutException ex) {
			LOGGER.error(RABBIT_MQ, () -> CommonLogMessageBuilder.create()
				.addMsg("Ошибка оправки сообщения")
				.build(), ex);
			throw new RabbitMqException("Ошибка отправки сообщения", ex);
		}
	}

	/**
	 * Определение получателя сообщений
	 *
	 * @param instance  описание инстанса рэббита
	 * @param queueName имя очереди
	 * @param callback  получатель сообщений
	 * @throws RabbitMqException ошибка отправки сообщений
	 */
	@SuppressWarnings({"PMD.LawOfDemeter", "PMD.CloseResource"})
	public static void simpleReceiveStringMessages(final RabbitMqInstanceParameters instance, final String queueName,
		final AbstractMessageReceiver callback) throws RabbitMqException {
		try {
			final Connection connection = ConnectionFactoryMapper.getInstance().getConnection(instance);
			final Channel channel = connection.createChannel();
			callback.setConnection(connection);
			callback.setChannel(channel);
			channel.queueDeclare(queueName, false, false, false, null);
			channel.basicConsume(queueName, true, callback, consumerTag -> { });
		} catch (IOException | TimeoutException ex) {
			LOGGER.error(RABBIT_MQ, () -> CommonLogMessageBuilder.create()
				.addMsg("Ошибка определения получателя сообщений")
				.build(), ex);
			throw new RabbitMqException("Ошибка определения получателя сообщений", ex);
		}
	}
}
