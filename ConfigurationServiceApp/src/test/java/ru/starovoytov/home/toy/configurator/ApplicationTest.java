package ru.starovoytov.home.toy.configurator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.starovoytov.home.toy.common.libs.exceptions.ResourceException;
import ru.starovoytov.home.toy.common.libs.resource.ResourceHelper;
import ru.starovoytov.home.toy.configurator.configuration.Configurator;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.starovoytov.home.toy.configurator.configuration.ConfigurationParametersHelper.PROPERTY_FILE;

/**
 * Тест сервиса {@link Application}
 *
 * @author starovoytov
 * @since 2019.12.21
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@SuppressWarnings({"PMD.AtLeastOneConstructor", "PMD.TooManyStaticImports", "PMD.AvoidPrintStackTrace"})
public class ApplicationTest {
	@Autowired
	private transient MockMvc mvc;

	static {
		try {
			Configurator.getInstance()
				.setFinalParameter(PROPERTY_FILE, ResourceHelper.getFileUrlFromResources("/test.properties"));
		} catch (ResourceException ex) {
			ex.printStackTrace();
		}
	}

	@Test
	@SuppressWarnings({"PMD.LawOfDemeter", "PMD.SignatureDeclareThrowsException"})
	public void helloController() throws Exception {
		final MvcResult result = mvc.perform(get("/hello")).andExpect(status().isOk()).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Hello!"), "Bad hello text");
	}

	@Test
	@SuppressWarnings({"PMD.LawOfDemeter", "PMD.SignatureDeclareThrowsException", "PMD.JUnitTestsShouldIncludeAssert"})
	public void testGetController() throws Exception {
		mvc.perform(get("/get/value?name=key1")).andExpect(status().isOk()).andExpect(content().string("value1"));
	}
}
