package ru.job4j.list;

/**
 * Класс - определитель цикличности в узлах.
 * @author Alexey Makarov
 * @since 19.08.18
 * @version 0.1
 */
public class CycleNode {

    /**
     * Имеет ли последовательность узлов цикличность.
     * @param first первый узел.
     * @return есть ли цикличность.
     */
    public boolean hasCycle(Node first) {
        boolean result = false;
        Node current = first.next;
        if (current != null) {
            while (true) {
                if (current == first) {
                    result = true;
                    break;
                } else if (current.next != null && current.next.next != null) {
                    first = first.next;
                    current = current.next.next;
                } else {
                    break;
                }
            }
        }
        return result;
    }
}

class Node<T> {
    T value;
    Node<T> next;
}