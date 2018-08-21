package ru.job4j.set;

import ru.job4j.list.CustomArrayList;

import java.util.Deque;
import java.util.Iterator;

/**
 * Класс - простое множество, без дубликатов.
 * @author Alexey Makarov
 * @since 17.08.18
 * @version 0.1
 */
public class SimpleSet<T> implements Iterable<T> {
    private CustomArrayList<T> list = new CustomArrayList<>();

    /**
     * Метод добавляет объект к списку только если такого объекта ещё в нем нет.
     * @param obj объект.
     */
    public void add(T obj) {
        for (T current : list) {
            if (current.equals(obj)) {
                return;
            }
        }
        list.add(obj);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
