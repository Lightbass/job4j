package ru.job4j.cache;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс - неблокирующий кеш для моделей.
 * @author Alexey Makarov
 * @since 08.09.18
 * @version 0.1
 */
public class BaseCache {
    private ConcurrentHashMap<Integer, Base> map = new ConcurrentHashMap<>();

    /**
     * Метод возвращает модель из отображения.
     * @param id идентификатор.
     * @return модель.
     */
    public Base get(Integer id) {
        Base result = null;
        if (map.containsKey(id)) {
            try {
                result = (Base) map.get(id).clone();
            } catch (CloneNotSupportedException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Метод добавляет модель в отображение.
     * @param model модель.
     */
    public void add(Base model) {
        map.putIfAbsent(model.getId(), model);
    }

    /**
     * Метод обновляет модель в отображении.
     * @param model
     * @throws OptimisticException если модель уже была изменена.
     */
    public void update(Base model) throws OptimisticException {
        map.computeIfPresent(model.getId(), (k, v) -> {
            if (v.getVersion() >= model.getVersion()) {
                throw new OptimisticException("This model has been updated.");
            }
            return model;
        });
    }

    /**
     * Метод удаляет модель из отображения.
     * @param model
     */
    public void delete(Base model) {
        map.remove(model.getId());
    }
}
