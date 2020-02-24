package ru.starovoytov.home.toy.telegram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ru.starovoytov.home.toy.common.libs.exceptions.RabbitMqException;
import ru.starovoytov.home.toy.telegram.bot.TelegramBuilder;
import ru.starovoytov.home.toy.telegram.configuration.Configurator;
import ru.starovoytov.home.toy.telegram.exceptions.TelegramConnectionException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Стартовый класс сервиса отправки сообщений в telegram
 *
 * @author starovoytov
 * @since 2020.02.16
 */
@SpringBootApplication
@ComponentScan({"ru.starovoytov.home.toy.springboot.library.controllers", "ru.starovoytov.home.toy.telegram"})
@SuppressWarnings({"PMD.UseUtilityClass"})
public class Application {
	/**
	 * Стартовый метод
	 *
	 * @param args аргументы
	 * @throws TelegramConnectionException ошибка подключения к телеграм
	 * @throws RabbitMqException           ошибка подключения к рэббиту
	 */
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public static void main(final String[] args) throws TelegramConnectionException, RabbitMqException {

		if ("YES".equals(Configurator.getInstance().getTelegramBotStart())) {
			new TelegramBuilder().addBotName(Configurator.getInstance().getTelegramBotName())
				.addBotToken(Configurator.getInstance().getTelegramBotToken())
				.addRabbitParameters(Configurator.getInstance().getRabbitMqInstanceParameters())
				.addRabbitQueueName(Configurator.getInstance().getRabbitQueueName())
				.build();
		}

		final SpringApplication app = new SpringApplication(Application.class);
		final Map<String, Object> properties = new ConcurrentHashMap<>();
		properties.put("server.port", Configurator.getInstance().getPort());
		properties.put("server.host", Configurator.getInstance().getHost());
		app.setDefaultProperties(properties);
		app.run(args);
	}
}
