package ru.job4j.generic;

/**
 * Класс - простой связный список.
 * @author Alexey Makarov
 * @since 17.08.18
 * @version 0.1
 */
public class SimpleArrayList<E> {

    private int size;
    private Node<E> first;

    /**
     * Метод вставляет в начало списка данные.
     * @param data вставляемы данные.
     */
    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Метод удаления первого элемента в списке.
     * @return объект, который был удален.
     */
    public E delete() {
        Node<E> result = this.first;
        this.first = this.first.next;
        this.size--;
        return (E) result;
    }

    /**
     * Метод получения элемента по индексу.
     * @param index индекс нужного элемента.
     * @return элемент.
     */
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.date;
    }

    /**
     * Метод получения размера коллекции.
     * @return размер коллекции.
     */
    public int getSize() {
        return this.size;
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
}