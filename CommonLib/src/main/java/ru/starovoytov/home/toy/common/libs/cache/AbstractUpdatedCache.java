package ru.starovoytov.home.toy.common.libs.cache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.starovoytov.home.toy.common.libs.exceptions.UpdateCacheException;
import ru.starovoytov.home.toy.common.libs.log.CommonLogMessageBuilder;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicReference;

import static ru.starovoytov.home.toy.common.libs.log.MarkersHelper.UPDATE_CACHE;

/**
 * Обновляемый кэш
 *
 * @author starovoytov
 * @since 2020.01.08
 */
public abstract class AbstractUpdatedCache<T> implements UpdatedCache<T> {

	private static final Logger LOGGER = LogManager.getLogger(AbstractUpdatedCache.class);

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
		try {
			final T newEntity = getNewEntity();
			entity.set(newEntity);
		} catch (UpdateCacheException ex) {
			LOGGER.error(UPDATE_CACHE, () -> CommonLogMessageBuilder.create()
				.addMsg("Ошибка обновления кэша")
				.build(), ex);
		}
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
	 * @throws UpdateCacheException ошибка обновления кэша
	 */
	protected abstract T getNewEntity() throws UpdateCacheException;

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
