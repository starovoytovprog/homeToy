package ru.starovoytov.home.toy.vk.collector;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.starovoytov.home.toy.common.libs.exceptions.RabbitMqException;
import ru.starovoytov.home.toy.common.libs.rabbit.RabbitMqInstanceParameters;
import ru.starovoytov.home.toy.vk.configuration.Configurator;
import ru.starovoytov.home.toy.vk.log.VkCollectorLogMessageBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import static ru.starovoytov.home.toy.common.libs.log.MarkersHelper.RABBIT_MQ;
import static ru.starovoytov.home.toy.common.libs.rabbit.RabbitMqHelper.simpleSendStringMessages;

/**
 * Задача таймера для сбора информации со страниц ВК
 *
 * @author starovoytov
 * @since 2020.02.15
 */
public class VkTimerTask extends TimerTask {
	private static final Logger LOGGER = LogManager.getLogger(VkTimerTask.class);

	private final transient Collector collector;
	private final transient RabbitMqInstanceParameters parameters;
	private final transient String rabbitMqQueueName;
	private final transient List<String> notSendList;

	/**
	 * Конструктор задачи
	 *
	 * @param collector         сборщик постов со страниц вк
	 * @param parameters        параметры подключения к RabbitMQ
	 * @param rabbitMqQueueName имя очереди RabbitMQ
	 */
	public VkTimerTask(final Collector collector, final RabbitMqInstanceParameters parameters,
		final String rabbitMqQueueName) {
		super();
		this.collector = collector;
		this.parameters = parameters;
		this.rabbitMqQueueName = rabbitMqQueueName;
		this.notSendList = new ArrayList<>();
	}

	@Override
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public void run() {
		final List<String> messages = collector.getNextUrlList(Configurator.getInstance().getWallsId());
		messages.addAll(notSendList);
		notSendList.clear();
		try {
			simpleSendStringMessages(parameters, rabbitMqQueueName, messages);
		} catch (RabbitMqException ex) {
			notSendList.addAll(messages);
			LOGGER.error(RABBIT_MQ, () -> VkCollectorLogMessageBuilder.create()
				.addMsg("Ошибка отправки сообщений в rabbit")
				.build(), ex);
		}
	}
}
