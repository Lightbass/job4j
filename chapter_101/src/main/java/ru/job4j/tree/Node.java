package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс - узел.
 * @author Alexey Makarov
 * @since 27.08.18
 * @version 0.1
 */
public class Node<E extends Comparable<E>> {
    private final List<Node<E>> children = new ArrayList<>();
    private final E value;

    /**
     * Конструктор инициализирует входящее значение.
     * @param value
     */
    public Node(final E value) {
        this.value = value;
    }

    /**
     * Метод добавляет узел в дерево.
     * @param child дочерний объект.
     */
    public void add(Node<E> child) {
        this.children.add(child);
    }

    /**
     * Метод выводит список дочерних объектов.
     * @return коллекция с дочерними объектами.
     */
    public List<Node<E>> leaves() {
        return this.children;
    }

    /**
     * Метод сравнивает значение в параметрах с сохраненным в узле значением.
     * @param that сравниваемое значение.
     * @return true, если объекты равны; false, если нет.
     */
    public boolean eqValue(E that) {
        return this.value.compareTo(that) == 0;
    }

    /**
     * Метод возвращает значение из контейнера.
     * @return значение.
     */
    public E getValue() {
        return value;
    }

}