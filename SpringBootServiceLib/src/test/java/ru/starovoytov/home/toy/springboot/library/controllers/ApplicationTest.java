package ru.starovoytov.home.toy.springboot.library.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тест приложения для тестов SpringBoot {@link Application}
 *
 * @author starovoytov
 * @since 2020.02.01
 */
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
public class ApplicationTest {
	@Autowired
	private transient MockMvc mvc;

	@Test
	@SuppressWarnings({"PMD.LawOfDemeter", "PMD.SignatureDeclareThrowsException", "PMD.JUnitTestsShouldIncludeAssert"})
	public void helloGradle() throws Exception {
		mvc.perform(get("/")).andExpect(status().isOk()).andExpect(content().string("Hello Gradle!"));
	}
}
