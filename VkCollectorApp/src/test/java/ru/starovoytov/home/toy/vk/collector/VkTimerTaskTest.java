package ru.starovoytov.home.toy.vk.collector;

import com.rabbitmq.client.Delivery;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.starovoytov.home.toy.common.libs.exceptions.RabbitMqException;
import ru.starovoytov.home.toy.common.libs.rabbit.AbstractMessageReceiver;
import ru.starovoytov.home.toy.common.libs.rabbit.RabbitMqHelper;
import ru.starovoytov.home.toy.vk.configuration.Configurator;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.starovoytov.home.toy.vk.configuration.ConfigurationParametersHelper.VK_WALLS_ID;

/**
 * Тест задачи таймера для сбора информации со страниц ВК
 *
 * @author starovoytov
 * @since 2020.02.15
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
class VkTimerTaskTest {
	private static final String QUEUE_NAME = "vk test queue";
	private static final TestConfigurator CONFIGURATOR = new TestConfigurator();

	@BeforeAll
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public static void setParams() {
		Configurator.getInstance().setFinalParameter(VK_WALLS_ID, String.valueOf(CONFIGURATOR.getTestOwnerId()));
	}

	/**
	 * Тесто шага получения и отправки сообщений
	 *
	 * @throws RabbitMqException    Ошибка подключения к rabbit
	 * @throws InterruptedException прерывание
	 */
	@Test
	@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.LawOfDemeter"})
	public void testOneStep() throws RabbitMqException, InterruptedException {
		final Map<Integer, Long> map = new ConcurrentHashMap<>();
		map.put(CONFIGURATOR.getTestOwnerId(), 0L);
		final Collector collector = new Collector(map);
		try (TestDeliveryCallBack callBack = new TestDeliveryCallBack()) {
			RabbitMqHelper.simpleReceiveStringMessages(CONFIGURATOR.getRabbitInstanceParameters(), QUEUE_NAME, callBack);
			new VkTimerTask(collector, CONFIGURATOR.getRabbitInstanceParameters(), QUEUE_NAME).run();

			assertTrue(callBack.countDownLatch.await(5_000, TimeUnit.MILLISECONDS), "Don't await countDownLatch");
			assertTrue(callBack.messages.contains(CONFIGURATOR.getTestAddress()), "Bad receive message");
		}
	}

	/**
	 * Тест выполнения с ошибкой
	 */
	@Test
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public void testError() {
		final Map<Integer, Long> map = new ConcurrentHashMap<>();
		map.put(CONFIGURATOR.getTestOwnerId(), 0L);
		final Collector collector = new Collector(map);
		final VkTimerTask timerTask = new VkTimerTask(collector, CONFIGURATOR.getBadRabbitInstanceParameters(), QUEUE_NAME);
		timerTask.run();
		assertTrue(timerTask.getNotSendList().contains(CONFIGURATOR.getTestAddress()), "Not message in list");
	}

	/**
	 * Тестовый обработчик сообщений
	 */
	private static class TestDeliveryCallBack extends AbstractMessageReceiver {
		private final transient List<String> messages = new ArrayList<>();
		private final transient CountDownLatch countDownLatch = new CountDownLatch(4);

		@Override
		public void handle(final String consumerTag, final Delivery message) {
			messages.add(new String(message.getBody(), Charset.defaultCharset()));
			countDownLatch.countDown();
		}
	}
}
