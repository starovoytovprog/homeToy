package ru.starovoytov.home.toy.springboot.library.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
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
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class HelloControllerTest {
	@Autowired
	private MockMvc mvc;

	@Test
	public void helloController() throws Exception {
		MvcResult result = mvc.perform(get("/hello")).andExpect(status().isOk()).andReturn();
		assertTrue(result.getResponse().getContentAsString().contains("Hello!"));
	}
}
