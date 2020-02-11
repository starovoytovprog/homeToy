package ru.starovoytov.home.toy.common.libs.http;

import org.junit.jupiter.api.Test;
import ru.starovoytov.home.toy.common.libs.exceptions.HttpClientException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.starovoytov.home.toy.common.libs.http.RequestHelper.sendEmptyGetRequest;

/**
 * Тест отправки http-запросов {@link RequestHelper}
 *
 * @author starovoytov
 * @since 2020.02.11
 */
@SuppressWarnings({"PMD.AtLeastOneConstructor"})
class RequestHelperTest {
	private static final String TEST_UID = "TEST_UID";

	/**
	 * Тест отправки запроса по несуществующему адресу
	 */
	@Test
	@SuppressWarnings({"PMD.JUnitTestContainsTooManyAsserts", "PMD.LawOfDemeter"})
	public void badAddressTest1() {
		final Exception exception = assertThrows(HttpClientException.class, () -> sendEmptyGetRequest("bad address", TEST_UID));
		assertEquals("HTTP connection failed", exception.getMessage(), "Bad exception message");
	}
}
