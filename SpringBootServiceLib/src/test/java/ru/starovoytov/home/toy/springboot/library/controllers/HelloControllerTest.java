package ru.starovoytov.home.toy.springboot.library.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тест контроллера hello {@link HelloController}
 *
 * @author starovoytov
 * @since 2020.02.02
 */
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
public class HelloControllerTest {
	@Autowired
	private transient MockMvc mvc;

	@Test
	@SuppressWarnings({"PMD.LawOfDemeter", "PMD.SignatureDeclareThrowsException"})
	public void helloController() throws Exception {
		final MvcResult result = mvc.perform(get("/hello")).andExpect(status().isOk()).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Hello!"), "Bad hello text");
	}
}
