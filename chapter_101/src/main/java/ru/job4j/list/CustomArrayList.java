package ru.job4j.list;

import java.util.*;

/**
 * Класс - динамический список в основе которого лежит массив.
 * @author Alexey Makarov
 * @since 17.08.18
 * @version 0.1
 */
public class CustomArrayList<E> implements Iterable<E> {

    private long modCount;
    private Object[] data;
    private int size;

    private void checkBounds(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void extendData() {
        data = Arrays.copyOf(data, data.length + (data.length >> 1));
    }

    /**
     * Конструктор инициализирует массив данных.
     */
    public CustomArrayList() {
        data = new Object[10];
    }

    /**
     * Метод добавляет объект к списку.
     * @param model объект.
     */
    public void add(E model) {
        modCount++;
        if (data.length == size) {
            extendData();
        }
        data[size++] = model;
    }

    /**
     * Метод возвращает объект из контейнера.
     * @param index индекс нужного объекта.
     * @return возвращаемый объект по данному индексу.
     */
    public E get(int index) {
        checkBounds(index);
        return (E) data[index];
    }

    /**
     * Метод удаляет объект из контейнера.
     * @param index индекс удаляемого объекта.
     */
    public E delete(int index) {
        checkBounds(index);
        modCount++;
        --size;
        System.arraycopy(data, index + 1, data, index, size - index);
        E result = (E) data[size];
        data[size] = null;
        return result;
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
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int pos;
            final long fixedModCount = modCount;

            @Override
            public boolean hasNext() {
                checkMod();
                return pos != size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) data[pos++];
            }

            final private void checkMod() {
                if (fixedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
