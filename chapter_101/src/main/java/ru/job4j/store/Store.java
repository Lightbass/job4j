package ru.job4j.store;

/**
 * Интерфейс - контейнер.
 * @author Alexey Makarov
 * @since 17.08.18
 * @version 0.1
 */
public interface Store<T extends Base> {
    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);
}
