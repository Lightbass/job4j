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

    private void checkBounds(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node<E> findByIndex(int index) {
        checkBounds(index);
        Node<E> result = this.last.next;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result;
    }

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
        return findByIndex(index).data;
    }

    /**
     * Метод удаления первого элемента в списке.
     * @return объект, который был удален.
     */
    public E delete(int index) {
        Node<E> prev;
        prev = index == 0 ? last : findByIndex(index - 1);
        Node<E> onIndex = prev.next;
        prev.next = onIndex.next;
        modCount++;
        this.size--;
        return onIndex.data;
    }

    /**
     * Метод возвращает кол-во элементов в контейнере.
     * @return кол-во элементов.
     */
    public int size() {
        return size;
    }

    /**
     * Класс предназначен для хранения данных.
     */
    private class Node<E> {
        E data;
        Node<E> next;
        Node(E data) {
            this.data = data;
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
                checkMod();
                return pos != size;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = current.data;
                current = current.next;
                pos++;
                return result;
            }

            final private void checkMod() {
                if (fixedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
