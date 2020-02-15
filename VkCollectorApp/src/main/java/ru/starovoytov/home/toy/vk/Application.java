package ru.starovoytov.home.toy.vk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import ru.starovoytov.home.toy.vk.collector.Collector;
import ru.starovoytov.home.toy.vk.collector.VkTimerTask;
import ru.starovoytov.home.toy.vk.configuration.Configurator;

import java.util.Map;
import java.util.Timer;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Стартовый класс сервиса сбора сообщений из ВК
 *
 * @author starovoytov
 * @since 2020.02.12
 */
@SpringBootApplication
@ComponentScan({"ru.starovoytov.home.toy.springboot.library.controllers", "ru.starovoytov.home.toy.vk"})
@SuppressWarnings({"PMD.UseUtilityClass"})
public class Application {
	/**
	 * Стартовый метод
	 *
	 * @param args аргументы
	 */
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public static void main(final String[] args) {

		if ("true".equals(Configurator.getInstance().getRunCollect())) {
			final Collector collector = new Collector(new ConcurrentHashMap<>());
			final VkTimerTask timerTask = new VkTimerTask(collector, Configurator.getInstance()
				.getRabbitMqInstanceParameters(), Configurator.getInstance().getRabbitQueueName());

			new Timer().schedule(timerTask, 0, Configurator.getInstance().getVkUpdateInterval());
		}

		final SpringApplication app = new SpringApplication(Application.class);
		final Map<String, Object> properties = new ConcurrentHashMap<>();
		properties.put("server.port", Configurator.getInstance().getPort());
		properties.put("server.host", Configurator.getInstance().getHost());
		app.setDefaultProperties(properties);
		app.run(args);
	}
}
