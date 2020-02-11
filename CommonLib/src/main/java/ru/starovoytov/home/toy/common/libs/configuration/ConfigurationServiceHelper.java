package ru.starovoytov.home.toy.common.libs.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.starovoytov.home.toy.common.libs.exceptions.UidGenerationException;
import ru.starovoytov.home.toy.common.libs.log.CommonLogMessageBuilder;
import ru.starovoytov.home.toy.common.libs.log.MarkersHelper;

import static ru.starovoytov.home.toy.common.libs.generators.UidGeneratorHelper.hashUid;
import static ru.starovoytov.home.toy.common.libs.http.RequestHelper.httpEmptyGet;

/**
 * Помошник для работы с сервисом конфигурации
 *
 * @author starovoytov
 * @since 2020.02.10
 */
public final class ConfigurationServiceHelper {
	private static final String CONF_UID;
	private static final Logger LOGGER = LogManager.getLogger(ConfigurationServiceHelper.class);

	static {
		String uid = ConfigurationServiceHelper.class.getCanonicalName();
		try {
			uid = hashUid(uid, "SHA-1");
		} catch (UidGenerationException ex) {
			final String currentUid = uid;
			LOGGER.fatal(MarkersHelper.CONF_SERVICE_GENERATE_UID, () -> CommonLogMessageBuilder.create()
				.addMsg("HTTP connection failed")
				.addUid(currentUid)
				.build(), ex);
		}
		CONF_UID = uid;
	}

	/**
	 * Закрытый конструктор без параметров
	 */
	private ConfigurationServiceHelper() {
	}

	/**
	 * Получить значение параметра из сервиса конфигурации
	 *
	 * @param key     ключ параметра
	 * @param address адрес сервиса конфигурации
	 * @return значение параметра
	 */
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public static String getValueFromService(final String key, final String address) {
		final String sendAddress = address + "?name=" + key;
		final String response = httpEmptyGet(sendAddress, CONF_UID);
		return response == null || response.isEmpty() ? null : response;
	}
}
