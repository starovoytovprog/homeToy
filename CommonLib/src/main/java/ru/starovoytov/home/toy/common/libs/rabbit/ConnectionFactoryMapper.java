package ru.starovoytov.home.toy.common.libs.rabbit;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeoutException;

/**
 * Коллекция подключений к RabbitMq
 *
 * @author starovoytov
 * @since 2020.02.13
 */
@SuppressWarnings({"PMD.LongVariable"})
public final class ConnectionFactoryMapper {
	/**
	 * Инстанс коллекции
	 */
	private static final ConnectionFactoryMapper INSTANCE = new ConnectionFactoryMapper();

	/**
	 * Мапа фабрик подключений
	 */
	private final transient Map<RabbitMqInstanceParameters, ConnectionFactory> connectionFactories;

	/**
	 * private-конструктор
	 */
	private ConnectionFactoryMapper() {
		connectionFactories = new ConcurrentHashMap<>();
	}

	/**
	 * Получить инстанс коллекции
	 *
	 * @return инстанс коллекции
	 */
	public static ConnectionFactoryMapper getInstance() {
		return INSTANCE;
	}

	/**
	 * Получить коннект
	 *
	 * @param parameters параметры подключения
	 * @return подключение
	 * @throws IOException      ошибка
	 * @throws TimeoutException ошибка
	 */
	@SuppressFBWarnings({"JLM_JSR166_UTILCONCURRENT_MONITORENTER"})
	public Connection getConnection(final RabbitMqInstanceParameters parameters) throws IOException, TimeoutException {
		synchronized (connectionFactories) {
			ConnectionFactory factory = connectionFactories.get(parameters);

			if (factory == null) {
				factory = new ConnectionFactory();
				factory.setHost(parameters.getHost());
				factory.setUsername(parameters.getLogin());
				factory.setPassword(parameters.getPassword());
				connectionFactories.put(parameters, factory);
			}

			return factory.newConnection();
		}
	}
}
