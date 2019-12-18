package ru.starovoytov.home.toy.common.libs.generators;

import ru.starovoytov.home.toy.common.libs.exceptions.UidGenerationException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.concurrent.atomic.AtomicLong;
import javax.xml.bind.DatatypeConverter;

/**
 * Хэлпер для генерации идентификаторов операций
 *
 * @author starovoytov
 * @since 2019.12.10
 */
public final class UidGeneratorHelper {

	private static final AtomicLong TIME_MARKER = new AtomicLong(System.currentTimeMillis());

	/**
	 * Закрытый конструктор без параметров
	 */
	private UidGeneratorHelper() {
	}

	/**
	 * Сгенерировать идентификатор операции
	 *
	 * @return идентификатор операции
	 * @throws UidGenerationException ошибка при генерации uid
	 */
	public static String getNewUid() throws UidGenerationException {
		long rand = (long) (Math.random() * 1_000_000_000);
		rand *= TIME_MARKER.incrementAndGet() * System.currentTimeMillis();
		return hashUid(Long.toString(rand));
	}

	@SuppressWarnings({"PMD.LawOfDemeter"})
	private static String hashUid(final String uid) throws UidGenerationException {
		try {
			final MessageDigest msdDigest = MessageDigest.getInstance("SHA-1");
			msdDigest.update(uid.getBytes(StandardCharsets.UTF_8), 0, uid.length());
			final String sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
			return new String(Base64.getEncoder()
				                  .encode(sha1.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
		} catch (NoSuchAlgorithmException e) {
			throw new UidGenerationException("", e);
		}
	}
}
