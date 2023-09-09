package ru.job4j.consumer;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleBlockingQueueTest {

    @Test
    void whenConsumerWaitNewObjectThenWait() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(1);
        Thread consumer = new Thread(() -> {
            try {
                simpleBlockingQueue.poll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        consumer.start();
        Thread.sleep(50);
        assertEquals(Thread.State.WAITING, consumer.getState());
    }

    @Test
    void whenConsumerWaitNewObjectAndGetItThenOk() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(1);
        Thread consumer = new Thread(() -> {
            try {
                simpleBlockingQueue.poll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread producer = new Thread(() -> {
            try {
                simpleBlockingQueue.offer(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        consumer.start();
        Thread.sleep(50);
        producer.start();
        consumer.join();
        producer.join();

        assertEquals(Thread.State.TERMINATED, consumer.getState());
        assertEquals(Thread.State.TERMINATED, producer.getState());
    }

    @Test
    void whenQueueIsFullAndProducerIsWaiting() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(1);
        Thread producer = new Thread(() -> {
            try {
                simpleBlockingQueue.offer(10);
                simpleBlockingQueue.offer(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        producer.start();
        Thread.sleep(50);

        assertEquals(Thread.State.WAITING, producer.getState());
    }

    @Test
    void whenQueueIsFullAndConsumerNotifyProducerThenOk() throws InterruptedException {
        SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(2);
        Thread consumer = new Thread(() -> {
            try {
                simpleBlockingQueue.poll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread producer = new Thread(() -> {
            try {
                simpleBlockingQueue.offer(10);
                simpleBlockingQueue.offer(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        producer.start();
        Thread.sleep(50);
        consumer.start();
        consumer.join();
        producer.join();

        assertEquals(Thread.State.TERMINATED, consumer.getState());
        assertEquals(Thread.State.TERMINATED, producer.getState());
    }

    @Test
    public void whenFetchAllThenGetIt() throws InterruptedException {
        final CopyOnWriteArrayList<Integer> buffer = new CopyOnWriteArrayList<>();
        final SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>();
        Thread producer = new Thread(
                () -> IntStream.range(0, 5).forEach(i -> {
                    try {
                        queue.offer(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
        );
        producer.start();
        Thread consumer = new Thread(
                () -> {
                    while (!queue.isEmpty() || !Thread.currentThread().isInterrupted()) {
                        try {
                            buffer.add(queue.poll());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            Thread.currentThread().interrupt();
                        }
                    }
                }
        );
        consumer.start();
        producer.join();
        consumer.interrupt();
        consumer.join();
        assertThat(buffer).containsExactly(0, 1, 2, 3, 4);
    }
}
