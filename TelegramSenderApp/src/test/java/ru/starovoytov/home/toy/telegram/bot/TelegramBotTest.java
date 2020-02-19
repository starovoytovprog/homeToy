package ru.starovoytov.home.toy.telegram.bot;

import org.junit.jupiter.api.Test;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import ru.starovoytov.home.toy.telegram.TestConfigurator;
import ru.starovoytov.home.toy.telegram.configuration.Configurator;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест реализации бота для телеграм {@link TelegramBot}
 *
 * @author starovoytov
 * @since 2020.02.17
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
class TelegramBotTest {
	/**
	 * Тест подключения через прокси
	 */
	@Test
	@SuppressWarnings({"PMD.LawOfDemeter", "PMD.DataflowAnomalyAnalysis", "PMD.AvoidCatchingGenericException"})
	public void checkProxy() {
		final DefaultBotOptions options = new DefaultBotOptions();
		options.setProxyType(DefaultBotOptions.ProxyType.HTTP);
		options.setProxyHost(Configurator.getInstance().getTelegramProxyHost());
		options.setProxyPort(Configurator.getInstance().getTelegramProxyPort());
		final TelegramBot bot = new TelegramBot(options, TestConfigurator.getInstance()
			.getTestBotName(), TestConfigurator.getInstance().getTestBotToken());
		boolean isClear;
		try {
			bot.clearWebhook();
			isClear = true;
		} catch (Exception ex) {
			isClear = false;
		}

		assertTrue(isClear, "Don't clear web hooks.");
	}
}
