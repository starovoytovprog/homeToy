package ru.starovoytov.home.toy.common.libs.rabbit;

import com.rabbitmq.client.AlreadyClosedException;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.starovoytov.home.toy.common.libs.log.CommonLogMessageBuilder;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static ru.starovoytov.home.toy.common.libs.log.MarkersHelper.RABBIT_MQ;

/**
 * Получатель сообщений из RabbitMq
 *
 * @author starovoytov
 * @since 2020.02.14
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
public abstract class AbstractMessageReceiver implements DeliverCallback, Closeable {
	private static final Logger LOGGER = LogManager.getLogger(AbstractMessageReceiver.class);

	/**
	 * Подключение к rabbit
	 */
	private transient Connection connection;
	/**
	 * Канал до rabbit
	 */
	private transient Channel channel;

	/**
	 * Присвоить подключение
	 *
	 * @param connection подключение
	 */
	public final void setConnection(final Connection connection) {
		this.connection = connection;
	}

	/**
	 * Присвоить канал
	 *
	 * @param channel канал
	 */
	public final void setChannel(final Channel channel) {
		this.channel = channel;
	}

	/**
	 * Закрыть канал и подключение
	 */
	@Override
	public final void close() {
		try {
			if (channel != null) {
				channel.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (IOException | TimeoutException | AlreadyClosedException ex) {
			LOGGER.error(RABBIT_MQ, () -> CommonLogMessageBuilder.create()
				.addMsg("Ошибка при закрытии rabbit")
				.build(), ex);
		}
	}
}
