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
    private static final int CAPACITY = 3;
    private final Queue<T> queue = new LinkedList<>();

    /**
     * Метод добавляет элемент в блокирующую очередь.
     * @param value добавляемый элемент.
     * @throws InterruptedException
     */
    public void offer(final T value) {
        synchronized (this) {
            while (queue.size() == CAPACITY) {
                try {
                    wait();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
            queue.offer(value);
            notifyAll();
        }
    }

    /**
     * Метод возвращает элемент из блокирующей очереди.
     * @return элемент из очереди.
     * @throws InterruptedException
     */
    public T poll() throws InterruptedException  {
        synchronized (this) {
            while (queue.size() == 0) {
                wait();
            }
            notifyAll();
            return queue.poll();
        }
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
