package ru.starovoytov.home.toy.vk.collector;

import com.vk.api.sdk.actions.Wall;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.wall.WallPostFull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.starovoytov.home.toy.vk.configuration.Configurator;
import ru.starovoytov.home.toy.vk.exceptions.VkException;
import ru.starovoytov.home.toy.vk.log.VkCollectorLogMessageBuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static ru.starovoytov.home.toy.vk.log.MarkersHelper.VK_GET_POST_LIST;

/**
 * Помошник для работы с ВК
 *
 * @author starovoytov
 * @since 2020.02.15
 */
public final class VkHelper {
	private static final Logger LOGGER = LogManager.getLogger(VkHelper.class);

	/**
	 * Начальная часть адреса поста в ВК
	 */
	public static final String POST_URL_START = "https://vk.com/wall";

	/**
	 * Закрытый конструктор без параметров
	 */
	private VkHelper() {
	}

	/**
	 * Получить список адресов постов со стены
	 *
	 * @param ownerId   id стены
	 * @param startTime время, после которого возвращать посты
	 * @return список адресов постов со стены
	 * @throws VkException ошибка получения списка постов
	 */
	@SuppressWarnings({"PMD.LawOfDemeter", "PMD.DataflowAnomalyAnalysis"})
	public static List<String> getLastPostsUrl(final int ownerId, final long startTime) throws VkException {
		final int userId = Configurator.getInstance().getVkUserId();
		final String accessToken = Configurator.getInstance().getVkAccessToken();
		final UserActor userActor = new UserActor(userId, accessToken);
		final Wall wall = new Wall(new VkApiClient(HttpTransportClient.getInstance()));

		final List<String> resultList = new ArrayList();
		try {
			final List<WallPostFull> posts = wall.get(userActor).ownerId(ownerId).execute().getItems();
			posts.stream()
				.filter(post -> post.getDate() >= startTime)
				.sorted(Comparator.comparing(WallPostFull::getDate))
				.forEach(post -> resultList.add(getPostUrl(post)));
		} catch (ApiException | ClientException ex) {
			LOGGER.error(VK_GET_POST_LIST, () -> VkCollectorLogMessageBuilder.create()
				.addMsg("Ошибка получения списка постов")
				.build(), ex);
			throw new VkException("Ошибка получения списка постов", ex);
		}
		return resultList;
	}

	/**
	 * Получить url поста
	 *
	 * @param post пост
	 * @return url поста
	 */
	private static String getPostUrl(final WallPostFull post) {
		return POST_URL_START + post.getOwnerId() + "_" + post.getId();
	}
}
