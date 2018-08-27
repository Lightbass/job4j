package ru.job4j.tree;

import java.util.*;

/**
 * Класс - дерево.
 * @author Alexey Makarov
 * @since 27.08.18
 * @version 0.1
 */
public class Tree<E extends Comparable<E>> implements SimpleTree<E> {

    /**
     * Корневой узел.
     */
    Node<E> root;
    long modCount;

    /**
     * Конструктор инициализирует корневой узел, в который вставляется значение.
     * @param rootObj значение.
     */
    public Tree(E rootObj) {
        if (rootObj == null) {
            throw new NullPointerException();
        }
        root = new Node<>(rootObj);
    }

    /**
     * Метод добавляет узел с значением в другой узел.
     * @param parent значение родительского узла в который надо вставить дочерний.
     * @param child значение дочернего узла.
     * @return true - вставка успешно произошла; false - вставка не произошла.
     */
    @Override
    public boolean add(E parent, E child) {
        boolean result = false;
        if (parent == null || child == null) {
            throw new NullPointerException();
        }
        if (!findBy(child).isPresent()) {
            Optional<Node<E>> value = findBy(parent);
            if (value.isPresent()) {
                result = true;
                value.get().add(new Node<>(child));
                modCount++;
            }
        }
        return result;
    }

    /**
     * Поиск узла по значению.
     * @param value значение по которому нужно найти узел.
     * @return узел.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    /**
     * Метод возвращает итератор значений дерева.
     * @return итератор значений.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            final Queue<Iterator<Node<E>>> poolIter = new LinkedList<>();
            final long fixedModCount = modCount;
            Iterator<Node<E>> iter;
            Node<E> current = root;


            {
                prepareIterators(root);
                iter = poolIter.poll();
            }

            /**
             * Метод добавляет в пулл итераторов итераторы всех родителей.
             * @param node начальный узел.
             */
            private void prepareIterators(Node<E> node) {
                if (!node.leaves().isEmpty()) {
                    poolIter.offer(node.leaves().iterator());
                    for (Node<E> parent : node.leaves()) {
                       prepareIterators(parent);
                    }
                }
            }

            @Override
            public boolean hasNext() {
                checkMod();
                boolean result = true;
                if (current == null) {
                    result = false;
                }
                return result;
            }

            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E result = current.getValue();
                if (!iter.hasNext()) {
                    iter = poolIter.poll();
                    current = iter == null ? null : iter.next();
                } else {
                    current = iter.next();
                }
                return result;
            }

            private void checkMod() {
                if (fixedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
