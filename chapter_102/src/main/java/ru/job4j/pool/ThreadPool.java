package ru.job4j.pool;

import ru.job4j.wait.SimpleBlockingQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс - пул потоков.
 * @author Alexey Makarov
 * @since 10.09.18
 * @version 0.1
 */
public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>();
    private final int size = Runtime.getRuntime().availableProcessors();

    /**
     * Конструктор инициализирует потоки и запускает их на выполнение добавляемых задач.
     */
    public ThreadPool() {
        for (int i = 0; i != size; i++) {
            threads.add(new Thread(() -> {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        tasks.poll().run();
                    } catch (InterruptedException ie) {
                        Thread.currentThread().interrupt();
                    }
                }
            }, "PoolThread-" + i));
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    /**
     * Метод добавляет задачу в блокирующую очередь.
     * @param job задача для выполнения.
     */
    public void work(Runnable job) {
        tasks.offer(job);
    }

    /**
     * Метод завершает все потоки и дальнейшее выполнение задач.
     */
    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }
}
