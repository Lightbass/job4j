package ru.job4j.store;

import ru.job4j.generic.SimpleArray;

import java.util.Iterator;

/**
 * Класс - хранилище объектов.
 * @author Alexey Makarov
 * @since 17.08.18
 * @version 0.1
 */
public abstract class AbstractStore<E extends Base> implements Store<E> {
    private SimpleArray<E> data;

    private int findIndexById(String id) {
        int result = -1;
        for (int i = 0; i != data.size(); i++) {
            if (data.get(i).getId().equals(id)) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * Конструктор инициализирует кол-во моделей в контейнере SimpleArray.
     * @param n количество элементов.
     */
    public AbstractStore(int n) {
        data = new SimpleArray(n);
    }

    /**
     * Метод добавляет модель к списку.
     * @param obj объект.
     */
    @Override
    public void add(E obj) {
        data.add(obj);
    }

    /**
     * Метод изменяет объект в списке.
     * @param id идентификатор модели.
     * @param obj новая модель.
     */
    @Override
    public boolean replace(String id, E obj) {
        boolean result = false;
        int index = findIndexById(id);
        if (index != -1) {
            data.set(index, obj);
            result = true;
        }
        return result;
    }

    /**
     * Метод удаляет модель из контейнера.
     * @param id идентификатор удаляемой модели.
     */
    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = findIndexById(id);
        if (index != -1) {
            data.delete(index);
            result = true;
        }
        return result;
    }

    /**
     * Метод возвращает модель из контейнера.
     * @param id идентификатор нужной модели.
     * @return возвращаемая модель по данному индексу.
     */
    @Override
    public E findById(String id) {
        E result = null;
        int index = findIndexById(id);
        if (index != -1) {
            result = data.get(index);
        }
        return result;
    }
}
