package ru.job4j.exam;

import java.util.concurrent.CountDownLatch;

/**
 * Класс - гарантированный перекрестный deadlock для двух потоков, которые будут выполнять данную задачу.
 * @author Alexey Makarov
 * @since 13.09.18
 * @version 0.1
 */
public class DeadLock implements Runnable {
    final private CountDownLatch latch = new CountDownLatch(2);
    final private Object[] monitors = new Object[2];
    private int i = 1;

    /**
     * Инициализация объектов для критических секций.
     */
    public DeadLock() {
        monitors[0] = new Object();
        monitors[1] = new Object();
    }

    /**
     * Метод запускает задачу. Первая критическая секция сделана для того, чтобы потоки точно получили разный индекс
     * объектов в массиве. А далее уже идет конструкция из двух критических секций, которые создают deadlock.
     */
    @Override
    public void run() {
        int obj;
        synchronized (latch) {
            obj = i--;
        }
        synchronized (monitors[obj]) {
            latch.countDown();
            try {
                latch.await();
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
            synchronized (monitors[1 - obj]) {
                System.out.println("This message never be printed.");
            }
        }
    }
}
