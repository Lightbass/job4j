package ru.job4j.list;

import java.util.LinkedList;

/**
 * Класс - простой стак.
 * @author Alexey Makarov
 * @since 17.08.18
 * @version 0.1
 */
public class SimpleStack<T> {
    CustomArrayList<T> data = new CustomArrayList<>();

    /**
     * Метод выдает первый в очереди элемент.
     * @return элемент.
     */
    public T poll() {
        return data.delete(data.size() - 1);
    }

    /**
     * Метод добавляет элемент в очередь.
     * @param value добавляемый элемент.
     */
    public void push(T value) {
        data.add(value);
    }
}
