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
     * Метод определяет бинарное дерево.
     * @return true, если дерево бинарное; false, если нет;
     */
    public boolean isBinary() {
        return isBinary(root);
    }

    private boolean isBinary(Node<E> root) {
        if (root.leaves().size() == 1 || root.leaves().size() == 2) {
            for (Node<E> parent : root.leaves()) {
                if (!isBinary(parent)) {
                    return false;
                }
            }
            return true;
        }
        return root.leaves().size() == 0 ? true : false;
    }

    /**
     * Метод определяет бинарное дерево.
     * @return true, если дерево бинарное; false, если нет;
     */
    public boolean isBinary2() {
        boolean result = true;
        Iterator<Node<E>> iter = nodeIterator();
        while (iter.hasNext()) {
            if (iter.next().leaves().size() > 2) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * Метод возвращает итератор значений дерева.
     * @return итератор значений.
     */
    public Iterator<Node<E>> nodeIterator() {
        return new Iterator<Node<E>>() {
            final Queue<Iterator<Node<E>>> poolIter = new LinkedList<>();
            final long fixedModCount = modCount;
            Iterator<Node<E>> iter;

            {
                poolIter.offer(Arrays.asList(root).iterator());
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
                while (!iter.hasNext()) {
                    if (poolIter.isEmpty()) {
                        result = false;
                        break;
                    }
                    iter = poolIter.poll();
                }
                return result;
            }

            @Override
            public Node<E> next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> result = iter.next();
                return result;
            }

            private void checkMod() {
                if (fixedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }

    /**
     * Метод возвращает итератор значений дерева.
     * @return итератор значений.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Iterator<Node<E>> iter = nodeIterator();

            @Override
            public boolean hasNext() {
                return iter.hasNext();
            }

            @Override
            public E next() {
                return iter.next().getValue();
            }
        };
    }
}
