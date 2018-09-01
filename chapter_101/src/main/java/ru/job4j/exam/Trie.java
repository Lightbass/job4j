package ru.job4j.exam;

import java.util.LinkedList;
import java.util.List;

/**
 * Класс реализует префиксное дерево.
 * @author Alexey Makarov
 * @since 01.09.18
 * @version 0.1
 */
public class Trie<V> {

    /**
     * Корень дерева.
     */
    private Node root = new Node(' ');

    /**
     * Метод добавляет объект в дерево.
     * @param key ключ.
     * @param value значение.
     */
    public void add(String key, V value) {
        char[] chars = key.toCharArray();
        Node current = root;
        for (char character : chars) {
            Node node = findNodeByChar(current, character);
            if (node == null) {
                node = new Node(character);
                current.child.add(node);
            }
            current = node;
        }
        current.value = value;
    }

    /**
     * Метод находит значение по ключу.
     * @param key ключ.
     * @return значение.
     */
    public V get(String key) {
        char[] chars = key.toCharArray();
        Node current = root;
        for (char character : chars) {
            Node node = findNodeByChar(current, character);
            if (node == null) {
                current = null;
                break;
            }
            current = node;
        }
        return current == null ? null : current.value;
    }

    private Node findNodeByChar(Node parent, char ch) {
        Node result = null;
        for (Node node : parent.child) {
            if (ch == node.partKey) {
                result = node;
                break;
            }
        }
        return result;
    }

    /**
     * Класс - узел дерва.
     */
    private class Node {
        private char partKey;
        private V value;
        private List<Node> child = new LinkedList<>();
        Node(char partKey) {
            this.partKey = partKey;
        }
    }
}
