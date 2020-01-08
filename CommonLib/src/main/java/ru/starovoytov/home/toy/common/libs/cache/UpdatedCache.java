package ru.starovoytov.home.toy.common.libs.cache;

/**
 * Интерфейс кэша
 *
 * @author starovoytov
 * @since 2020.01.08
 */
public interface UpdatedCache<T> {
	/**
	 * Получить экземпляр из кэша
	 *
	 * @return экземпляр
	 */
	T getEntity();

	/**
	 * Обновить кэш
	 */
	void update();

	/**
	 * Получить отображение кэша
	 *
	 * @return отображение кэша
	 */
	String displayEntity();
}
