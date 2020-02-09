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
 * Тест контроллера для получения информации о кэше {@link GetCacheController}
 *
 * @author starovoytov
 * @since 2020.02.09
 */
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
public class GetCacheControllerTest {
	@Autowired
	private transient MockMvc mvc;

	@Test
	@SuppressWarnings({"PMD.LawOfDemeter", "PMD.SignatureDeclareThrowsException", "PMD.JUnitTestsShouldIncludeAssert"})
	public void badCacheName() throws Exception {
		mvc.perform(get("/caches/get?name=bad")).andExpect(status().isOk()).andExpect(content().string(""));
	}

	@Test
	@SuppressWarnings({"PMD.LawOfDemeter", "PMD.SignatureDeclareThrowsException", "PMD.JUnitTestsShouldIncludeAssert"})
	public void goodCacheName() throws Exception {
		mvc.perform(get("/caches/get?name=good")).andExpect(status().isOk()).andExpect(content().string("ok"));
	}
}
