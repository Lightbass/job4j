package ru.job4j.wait;

import org.junit.Test;
import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса SimpleBlockingQueue.
 * @author Alexey Makarov
 * @since 08.09.18
 * @version 0.1
 */
public class SimpleBlockingQueueTest {

    /**
     * Класс вставляет элементы в блокирующую очередь.
     */
    class Producer<T> extends Thread {
        private Queue<T> queue;
        private SimpleBlockingQueue<T> block;

        public Producer(Queue<T> queue, SimpleBlockingQueue<T> block) {
            this.queue = queue;
            this.block = block;
        }

        @Override
        public void run() {
            try {
                while (!queue.isEmpty()) {
                    block.offer(queue.poll());
                }
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    /**
     * Класс вытаскивает элементы из блокирующей очереди.
     */
    class Consumer<T> extends Thread {
        private Queue<T> queue;
        private SimpleBlockingQueue<T> block;
        private int capacity;

        public Consumer(Queue<T> queue, SimpleBlockingQueue<T> block, int capacity) {
            this.queue = queue;
            this.block = block;
            this.capacity = capacity;
        }

        @Override
        public void run() {
            try {
                while (queue.size() != capacity) {
                    queue.offer(block.poll());
                }
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    @Test
    public void whenOffer10ElementsToQueueAndGetItBack() throws InterruptedException {
        Integer[] i = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        LinkedList<Integer> input = new LinkedList(Arrays.asList(i));
        LinkedList<Integer> result = new LinkedList<>();
        SimpleBlockingQueue<Integer> block = new SimpleBlockingQueue<>();
        Producer<Integer> producer = new Producer<>(new LinkedList<>(input), block);
        Consumer<Integer> consumer = new Consumer<>(result, block, input.size());
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(result, is(input));
    }
}
