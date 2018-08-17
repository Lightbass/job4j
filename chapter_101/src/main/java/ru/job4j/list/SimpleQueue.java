package ru.job4j.list;

/**
 * Класс - простая очередь.
 * @author Alexey Makarov
 * @since 17.08.18
 * @version 0.1
 */
public class SimpleQueue<T> {
    CustomLinkedList<T> data = new CustomLinkedList<>();;

    /**
     * Метод выдает первый в очереди элемент.
     * @return элемент.
     */
    public T poll() {
        return (T) data.delete(0);
    }

    /**
     * Метод добавляет элемент в очередь.
     * @param value добавляемый элемент.
     */
    public void push(T value) {
        data.add(value);
    }
}
