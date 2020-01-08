package ru.starovoytov.home.toy.common.libs.cache;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Обновляемый кэш
 *
 * @author starovoytov
 * @since 2020.01.08
 */
public abstract class AbstractUpdatedCache<T> implements UpdatedCache<T> {

	/**
	 * Граница, ниже которой таймер не инициализуется
	 */
	private static final long START_BORDER = 0L;

	/**
	 * Экземпляр, хранящийся в кэше
	 */
	private final transient AtomicReference<T> entity;

	/**
	 * интервал между обновлениями данных в кэше в миллисекундах
	 */
	private final transient long updateInterval;

	/**
	 * Таймер обновления экземпляра
	 */
	private transient Timer timer;

	/**
	 * Конструктор кэша
	 *
	 * @param updateInterval интервал автоматического обновления кэша
	 * @param params         дополнительные параметры
	 */
	protected AbstractUpdatedCache(final long updateInterval, final Map<String, Object> params) {
		setParams(params);
		entity = new AtomicReference<>();
		this.updateInterval = updateInterval;
		startAndLoad();
	}

	@Override
	public T getEntity() {
		return entity.get();
	}

	@Override
	public final void update() {
		final T newEntity = getNewEntity();
		entity.set(newEntity);
	}

	@Override
	@SuppressWarnings({"PMD.LawOfDemeter"})
	public String displayEntity() {
		return entity.get().toString();
	}

	/**
	 * Установить параметры
	 *
	 * @param params параметры
	 */
	protected abstract void setParams(Map<String, Object> params);

	/**
	 * Получить новый экземпляр
	 *
	 * @return новый экземпляр
	 */
	protected abstract T getNewEntity();

	/**
	 * Загрузить первоначальное значение кэша и запустить таймер обновления
	 */
	private void startAndLoad() {
		update();
		if (updateInterval > START_BORDER) {
			timer = new Timer();
			timer.schedule(new UpdateSchedule(), updateInterval, updateInterval);
		}
	}

	/**
	 * Задание для обновления экземпляра
	 */
	@SuppressWarnings({"PMD.AtLeastOneConstructor"})
	private class UpdateSchedule extends TimerTask {
		/**
		 * Обновить экземпляр
		 */
		@Override
		public void run() {
			update();
		}
	}
}
