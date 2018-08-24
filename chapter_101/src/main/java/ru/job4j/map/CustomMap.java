package ru.job4j.map;

import java.util.*;

/**
 * Класс - отображение ключ-значение.
 * @author Alexey Makarov
 * @since 23.08.18
 * @version 0.1
 */
public class CustomMap<K, V> {

    private int threshold;
    private int capacity;
    private int size;
    private long modCount;
    private Node<K, V>[] data;

    /**
     * Конструктор инициализирует массив данных с помощью переменной capacity, которая будет приведена к ближайшему
     * числу 2 в степени n больше capacity. Также с помощью переменной loadFactor инициализируется переменная
     * threshold, которая контроллирует кол-во ячеек в массиве.
     * @param capacity
     * @param loadFactor
     */
    public CustomMap(int capacity, float loadFactor) {
        this.capacity = getTrueCapacity(capacity);
        if (loadFactor > 0 && loadFactor <= 1f) {
            threshold = (int) (loadFactor * capacity);
        } else {
            threshold = (int) (0.75f * capacity);
        }
        data = new Node[capacity];
    }

    /**
     * Стандартный конструктор, выставляющий значения по умолчанию.
     */
    public CustomMap() {
        this(16, 0.75f);
    }

    /**
     * Класс - узел.
     * @param <K> объект класса ключ.
     * @param <V> объект класса значение.
     */
    private class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> next;
        Node(K key, V value, Node<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    private int getTrueCapacity(int capacity) {
        capacity--;
        capacity |= capacity >> 1;
        capacity |= capacity >> 2;
        capacity |= capacity >> 4;
        capacity |= capacity >> 8;
        capacity |= capacity >> 16;
        capacity++;
        return capacity;
    }

    /**
     * Вычисление индекса по хешу ключа, по которому будет вставка в массив.
     * @param key ключ.
     * @return индекс нужной ячейки.
     */
    private int getIndex(K key) {
        return key == null ? 0 : (key.hashCode() & 0x7FFFFFFF) % capacity;
    }

    /**
     * Увеличения массива данных в два раза и перераспределение всех ключей-значенй в увеличенный массив.
     * Также увеличение порога элементов для следующего вызова resize().
     */
    private void resize() {
        int oldCap = capacity;
        capacity = capacity << 1;
        Node<K, V>[] dataOld = data;
        data = new Node[capacity];
        threshold = threshold << 1;
        size = 0;
        for (int i = 0; i != oldCap; i++) {
            Node<K, V> first = dataOld[i];
            dataOld[i] = null;
            while (first != null) {
                first.next = null;
                put(first.key, first.value);
                first = first.next;
            }
        }
    }

    /**
     * Вставка ключа-значения в массив данных.
     * @param key ключ.
     * @param value значение.
     * @return true - вставка произошла, false - не произошла.
     */
    public boolean put(K key, V value) {
        boolean result = true;
        int index = getIndex(key);
        Node<K, V> node = data[index];
        if (node == null) {
            data[index] = new Node(key, value, null);
            ++size;
            if (size >= threshold) {
                resize();
            }
            result = true;
            modCount++;
        } else {
            while (node != null) {
                if (node.key == key || (node.key.hashCode() == key.hashCode() && node.key.equals(key))) {
                    node.value = value;
                    result = true;
                    break;
                }
                if (node.next == null) {
                    node.next = new Node<K, V>(key, value, null);
                    modCount++;
                }
                node = node.next;
            }
        }
        return result;
    }

    /**
     * Получение значения по ключу.
     * @param key ключ.
     * @return значение, которое было привязано к данному ключу или null, если такого ключа не существует.
     */
    public V get(K key) {
        V result = null;
        int index = getIndex(key);
        Node<K, V> node = data[index];
        while (node != null) {
            if (node.key == key || (node.key.hashCode() == key.hashCode() && node.key.equals(key))) {
                result = node.value;
                break;
            }
            node = node.next;
        }
        return result;
    }

    /**
     * Удаляет элемент ключ-значение из массива данных.
     * @param key ключ.
     * @return true, если удаление прошло; false, если удаление не прошло.
     */
    public boolean delete(K key) {
        modCount++;
        boolean result = true;
        int index = getIndex(key);
        Node<K, V> prevNode = data[index];
        Node<K, V> node = prevNode;
        while (node != null) {
            if (node.key == key || (node.key.hashCode() == key.hashCode() && node.key.equals(key))) {
                result = true;
                if (node == prevNode) {
                    if (node.next == null) {
                        data[index] = null;
                        size--;
                    } else {
                        data[index] = node.next;
                    }
                } else {
                    if (node.next != null) {
                        prevNode.next = node.next;
                    } else {
                        prevNode.next = null;
                    }
                }
                break;
            }
            prevNode = node;
            node = node.next;
        }
        return result;
    }

    private Iterator<Node<K, V>> iterator() {
        return new Iterator<Node<K, V>>() {
            private int index;
            private final long fixedModCount = modCount;
            Node<K, V> node = data[index];

            @Override
            public boolean hasNext() {
                checkMod();
                boolean result = true;
                while (node == null) {
                    index++;
                    if (index == capacity) {
                        result = false;
                        break;
                    }
                    node = data[index];
                }
                return result;
            }

            @Override
            public Node<K, V> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<K, V> result = node;
                node = node.next;
                return result;
            }

            final private void checkMod() {
                if (fixedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    /**
     * Метод возвращает кол-во ячеек в массиве.
     * @return кол-во ячеек.
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Метод возвращает кол-во занятых ячеек в массиве.
     * @return кол-во занятых ячеек в массиве.
     */
    public int size() {
        return size;
    }

    /**
     * Возвращает итератор ключей.
     * @return итератор ключей.
     */
    public Iterator<K> keys() {
        return new Iterator<K>() {
            private Iterator<Node<K, V>> iter = iterator();

            @Override
            public boolean hasNext() {
               return iter.hasNext();
            }

            @Override
            public K next() {
                return iter.next().key;
            }
        };
    }

    /**
     * Возвращает итератор значений.
     * @return итератор значений.
     */
    public Iterator<V> values() {
        return new Iterator<V>() {
            private Iterator<Node<K, V>> iter = iterator();

            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public V next() {
                return iter.next().value;
            }
        };
    }
}
