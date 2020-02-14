package ru.starovoytov.home.toy.common.libs.rabbit;

import com.rabbitmq.client.Delivery;
import org.junit.jupiter.api.Test;
import ru.starovoytov.home.toy.common.libs.configuration.AbstractConfigurator;
import ru.starovoytov.home.toy.common.libs.exceptions.RabbitMqException;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест помошника для работы с RabbitMQ
 *
 * @author starovoytov
 * @since 2020.02.12
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
class RabbitMqHelperTest {
	private static final String QUEUE_NAME = "test queue";

	/**
	 * Тест отправки и получения сообщений
	 *
	 * @throws InterruptedException прерывание
	 * @throws RabbitMqException    ошибка при обращении к rabbit
	 */
	@Test
	@SuppressWarnings({"PMD.LawOfDemeter", "PMD.CloseResource", "PMD.JUnitTestContainsTooManyAsserts"})
	public void testSendAndReceive() throws RabbitMqException, InterruptedException {
		final TestConfigurator configurator = new TestConfigurator();
		final TestDeliveryCallBack callBack = new TestDeliveryCallBack();
		RabbitMqHelper.simpleReceiveStringMessages(configurator.getRabbitInstanceParameters(), QUEUE_NAME, callBack);
		final List<String> list = new ArrayList<>();
		list.addAll(Arrays.asList("1", "2", "3", "4"));
		RabbitMqHelper.simpleSendStringMessages(configurator.getRabbitInstanceParameters(), QUEUE_NAME, list);

		assertTrue(callBack.countDownLatch.await(500, TimeUnit.MILLISECONDS), "Don't await countDownLatch");
		assertTrue(list.containsAll(callBack.messages) && callBack.messages.containsAll(list), "Bad receive message");
		callBack.close();
		callBack.close();
	}

	/**
	 * Тест ошибки при отправке и получении сообщений
	 *
	 * @throws InterruptedException прерывание
	 * @throws RabbitMqException    ошибка при обращении к rabbit
	 */
	@Test
	@SuppressWarnings(
		{"PMD.LawOfDemeter", "PMD.CloseResource", "PMD.JUnitTestContainsTooManyAsserts", "PMD.DataflowAnomalyAnalysis"})
	public void testBadSendAndReceive() throws RabbitMqException, InterruptedException {
		final TestConfigurator configurator = new TestConfigurator();
		final TestDeliveryCallBack callBack = new TestDeliveryCallBack();
		Exception exception = assertThrows(RabbitMqException.class, () -> RabbitMqHelper.simpleReceiveStringMessages(configurator
			                                                                                                             .getBadRabbitInstanceParameters(), QUEUE_NAME, callBack));
		assertEquals("Ошибка определения получателя сообщений", exception.getMessage(), "Bad exception message");

		final List<String> list = new ArrayList<>();
		list.addAll(Arrays.asList("1", "2", "3", "4"));
		RabbitMqHelper.simpleSendStringMessages(configurator.getRabbitInstanceParameters(), QUEUE_NAME, list);

		exception = assertThrows(RabbitMqException.class, () -> RabbitMqHelper.simpleSendStringMessages(configurator.getBadRabbitInstanceParameters(), QUEUE_NAME, list));
		assertEquals("Ошибка отправки сообщения", exception.getMessage(), "Bad exception message");
	}

	/**
	 * Тестовый конфигуратор
	 */
	private static class TestConfigurator extends AbstractConfigurator {
		@Override
		protected void fillDefaultParameters() {
		}

		private RabbitMqInstanceParameters getRabbitInstanceParameters() {
			return new RabbitMqInstanceParameters(getStringParameter("TEST_RABBIT_MQ_HOST"), getStringParameter("TEST_RABBIT_MQ_LOGIN"), getStringParameter("TEST_RABBIT_MQ_PASSWORD"));
		}

		private RabbitMqInstanceParameters getBadRabbitInstanceParameters() {
			return new RabbitMqInstanceParameters("bad host", getStringParameter("TEST_RABBIT_MQ_LOGIN"), getStringParameter("TEST_RABBIT_MQ_PASSWORD"));
		}
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
