package ru.starovoytov.home.toy.common.libs.resource;

import ru.starovoytov.home.toy.common.libs.exceptions.ResourceException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Помощник для работы с ресурсами
 *
 * @author starovoytov
 * @since 2020.01.07
 */
public final class ResourceHelper {

	/**
	 * Bad path constant
	 */
	private static final String BAD_PATH = "Bad path: ";

	/**
	 * private-конструктор
	 */
	private ResourceHelper() {
	}

	/**
	 * Получить путь к файлу ресурсов
	 *
	 * @param path путь относительно ресурса
	 * @return путь к файлу ресурсов
	 * @throws ResourceException Ошибка пути к ресурсу
	 */
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public static String getFileUrlFromResources(final String path) throws ResourceException {
		try {
			final URL url = ResourceHelper.class.getResource(path);
			if (url == null) {
				throw new ResourceException(BAD_PATH + path, null);
			}
			return new File(url.toURI()).getCanonicalPath();
		} catch (URISyntaxException | IOException ex) {
			throw new ResourceException(BAD_PATH + path, ex);
		}
	}

	/**
	 * Загрузить контент из ресурса
	 *
	 * @param path путь до ресурса
	 * @return контент ресурса
	 * @throws ResourceException Ошибка пути к ресурсу
	 */
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public static String getFileContentFromResources(final String path) throws ResourceException {
		try (InputStream inputStream = ResourceHelper.class.getResourceAsStream(path)) {
			String content;
			if (inputStream == null) {
				throw new ResourceException(BAD_PATH + path, null);
			} else {
				content = new Scanner(inputStream, StandardCharsets.UTF_8.name()).useDelimiter("\\A").next();
			}
			return content;
		} catch (IOException ex) {
			throw new ResourceException(BAD_PATH + path, ex);
		}
	}
}
