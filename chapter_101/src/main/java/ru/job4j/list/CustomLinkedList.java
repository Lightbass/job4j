package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс - динамический связный список в основе.
 * @author Alexey Makarov
 * @since 17.08.18
 * @version 0.1
 */
public class CustomLinkedList<E> implements Iterable<E> {
    private long modCount;
    private int size;
    private Node<E> last;

    /**
     * Метод вставляет в начало списка данные.
     * @param data вставляемы данные.
     */
    public void add(E data) {
        modCount++;
        Node<E> newLink = new Node<>(data);
        if (this.last == null) {
            this.last = newLink;
            this.last.next = newLink;
        } else {
            newLink.next = this.last.next;
            this.last.next = newLink;
            last = newLink;
        }
        this.size++;
    }

    /**
     * Метод получения элемента по индексу.
     * @param index индекс нужного элемента.
     * @return элемент.
     */
    public E get(int index) {
        Node<E> result = this.last.next;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private static class Node<E> {

        E date;
        Node<E> next;

        Node(E date) {
            this.date = date;
        }
    }

    /**
     * Метод возвращает итератор контейнера.
     * @return итератор.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int pos;
            Node<E> current = last.next;
            final long fixedModCount = modCount;

            @Override
            public boolean hasNext() {
                return pos != size;
            }

            @Override
            public E next() {
                checkMod();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = current.date;
                current = current.next;
                pos++;
                return (E) result;
            }

            final private void checkMod() {
                if (fixedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
