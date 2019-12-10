package ru.starovoytov.home.toy.common.libs.generators;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.starovoytov.home.toy.common.libs.generators.UidGeneratorHelper.getNewUid;

/**
 * @author starovoytov
 * @since 2019.12.10
 */
final class UidGeneratorHelperTest {
	public UidGeneratorHelperTest() {}

	@Test
	public void testGetUid() {
		assertEquals("", getNewUid(), "Error in test generation unique uid");
	}
}
