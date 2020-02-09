package ru.starovoytov.home.toy.springboot.library.controllers.log;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import ru.starovoytov.home.toy.common.libs.cache.UpdatedCache;
import ru.starovoytov.home.toy.springboot.library.controllers.AppConfig;
import ru.starovoytov.home.toy.springboot.library.controllers.Application;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тест контроллера обновления кэшей {@link UpdateCacheController}
 *
 * @author starovoytov
 * @since 2020.02.09
 */
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
class UpdateCacheControllerTest {
	@Autowired
	@Qualifier("Caches")
	private transient Map<String, UpdatedCache> caches;

	@Autowired
	private transient MockMvc mvc;

	@Test
	@SuppressWarnings({"PMD.LawOfDemeter", "PMD.SignatureDeclareThrowsException"})
	public void updateCacheTest() throws Exception {
		mvc.perform(get("/caches/update"))
			.andExpect(status().isOk())
			.andExpect(content().string("Update caches\n\ngood: updated\n"));
		assertTrue(((AppConfig.TestCache) caches.get("good")).isUpdated(), "Cache didn't update");
	}
}
