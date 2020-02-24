package ru.starovoytov.home.toy.telegram;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.starovoytov.home.toy.telegram.configuration.Configurator;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.starovoytov.home.toy.telegram.configuration.ConfigurationParametersHelper.TELEGRAM_BOT_START;

/**
 * Тест сервиса {@link Application}
 *
 * @author starovoytov
 * @since 2020.02.16
 */
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@SuppressWarnings({"PMD.AtLeastOneConstructor", "PMD.TooManyStaticImports"})
class ApplicationTest {
	@Autowired
	private transient MockMvc mvc;

	@BeforeAll
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public static void setParams() {
		Configurator.getInstance().setFinalParameter(TELEGRAM_BOT_START, "NO");
	}

	@Test
	@SuppressWarnings({"PMD.LawOfDemeter", "PMD.SignatureDeclareThrowsException"})
	public void helloController() throws Exception {
		final MvcResult result = mvc.perform(get("/hello")).andExpect(status().isOk()).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Hello!"), "Bad hello text");
	}
}
