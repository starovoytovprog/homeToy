package ru.starovoytov.home.toy.telegram.bot;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ru.starovoytov.home.toy.common.libs.exceptions.RabbitMqException;
import ru.starovoytov.home.toy.common.libs.rabbit.RabbitMqHelper;
import ru.starovoytov.home.toy.telegram.TestConfigurator;
import ru.starovoytov.home.toy.telegram.configuration.Configurator;
import ru.starovoytov.home.toy.telegram.exceptions.TelegramConnectionException;

import java.util.Arrays;

/**
 * Тест конструктора телеграм-бота {@link TelegramBuilder}
 *
 * @author starovoytov
 * @since 2020.02.20
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
class TelegramBuilderTest {
	/**
	 * Тест конструктора
	 *
	 * @throws InterruptedException        прерывание
	 * @throws RabbitMqException           ошибка соединения с rabbit
	 * @throws TelegramConnectionException ошибка соединения с telegram
	 */
	@Test
	@Disabled
	@SuppressWarnings({"PMD.LawOfDemeter", "PMD.JUnitTestsShouldIncludeAssert", "PMD.UseUnderscoresInNumericLiterals"})
	public void testConstructor() throws InterruptedException, RabbitMqException, TelegramConnectionException {
		RabbitMqHelper.simpleSendStringMessages(Configurator.getInstance()
			                                        .getRabbitMqInstanceParameters(), "telegram_test_queue", Arrays.asList("тестовое сообщение"));
		new TelegramBuilder().addBotName(TestConfigurator.getInstance().getTestBotName())
			.addBotToken(TestConfigurator.getInstance().getTestBotToken())
			.addRabbitParameters(Configurator.getInstance().getRabbitMqInstanceParameters())
			.addRabbitQueueName("telegram_test_queue")
			.build();
		Thread.sleep(10000L);
	}
}
