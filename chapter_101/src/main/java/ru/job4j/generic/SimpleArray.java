package ru.job4j.generic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс - универсальная обёртка для массива.
 * @author Alexey Makarov
 * @since 16.08.18
 * @version 0.1
 */
public class SimpleArray<T> implements Iterable<T> {
    private int size;
    private final Object[] data;

    private void checkBounds(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Конструктор инициализирует кол-во элементов в массиве.
     * @param t кол-во элементов.
     */
    public SimpleArray(int t) {
        data = new Object[t];
    }

    /**
     * Метод добавляет объект к списку.
     * @param model объект.
     */
    public void add(T model) {
        if (data.length == size) {
            throw new IllegalStateException("Container is reach full capacity");
        }
        data[size++] = model;
    }

    /**
     * Метод изменяет объект в списке.
     * @param index индекс изменяемого объекта.
     * @param model новый объект.
     */
    public void set(int index, T model) {
        checkBounds(index);
        data[index] = model;
    }

    /**
     * Метод удаляет объект из контейнера.
     * @param index индекс удаляемого объекта.
     */
    public void delete(int index) {
        checkBounds(index);
        --size;
        System.arraycopy(data, index + 1, data, index, size - index);
        data[size] = null;
    }

    /**
     * Метод возвращает объект из контейнера.
     * @param index индекс нужного объекта.
     * @return возвращаемый объект по данному индексу.
     */
    public T get(int index) {
        checkBounds(index);
        return (T) data[index];
    }

    /**
     * Метод возвращает кол-во элементов в контейнере.
     * @return кол-во элементов.
     */
    public int size() {
        return size;
    }

    /**
     * Метод возвращает итератор контейнера.
     * @return итератор.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int pos;

            @Override
            public boolean hasNext() {
                return pos != size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                   throw new NoSuchElementException();
                }
                return (T) data[pos++];
            }
        };
    }
}
