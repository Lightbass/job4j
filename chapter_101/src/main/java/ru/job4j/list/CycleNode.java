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
        if (first != null) {
        Node current = first.next;
            while (current != null && current.next != null) {
                if (first == current) {
                    result = true;
                    break;
                }
                first = first.next;
                current = current.next.next;
            }
        }
        return result;
    }
}

class Node<T> {
    T value;
    Node<T> next;
}