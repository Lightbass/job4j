package ru.job4j.wait;


import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Класс реализует блокирующую очередь.
 * @author Alexey Makarov
 * @since 08.09.18
 * @version 0.1
 */
@ThreadSafe
public class SimpleBlockingQueue<T> {
    @GuardedBy("this")
    private final int capacity = 3;
    private final Queue<T> queue = new LinkedList<>();

    /**
     * Метод добавляет элемент в блокирующую очередь.
     * @param value добавляемый элемент.
     * @throws InterruptedException
     */
    public void offer(final T value) {
        synchronized (this) {
            while (queue.size() == capacity) {
                try {
                    wait();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
            queue.offer(value);
            notify();
        }
    }

    /**
     * Метод возвращает элемент из блокирующей очереди.
     * @return элемент из очереди.
     * @throws InterruptedException
     */
    public T poll() throws InterruptedException  {
        final T result;
        synchronized (this) {
            while (queue.size() == 0) {
                wait();
            }
            result = queue.poll();
            notify();
        }
        return result;
    }

    /**
     * Метод возвращает информацию о заполнении очереди.
     * @return true, если в очереди нет элементов; false, если в очереди есть элементы.
     */
    public boolean isEmpty() {
        synchronized (this) {
            return queue.isEmpty();
        }
    }
}
