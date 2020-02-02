package ru.starovoytov.home.toy.springboot.library.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тест приложения для тестов SpringBoot {@link App}
 *
 * @author starovoytov
 * @since 2020.02.01
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@AutoConfigureMockMvc
public class AppTest {
	@Autowired
	private MockMvc mvc;

	@Test
	public void helloGradle() throws Exception {
		mvc.perform(get("/")).andExpect(status().isOk()).andExpect(content().string("Hello Gradle!"));
	}
}