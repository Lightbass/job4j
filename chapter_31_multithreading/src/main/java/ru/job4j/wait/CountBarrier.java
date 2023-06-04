package ru.job4j.wait;

public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public static void main(String[] args) {
        System.out.println("Main thread started");
        CountBarrier countBarrier = new CountBarrier(2);
        Runnable threadTask = () -> {
            System.out.println(Thread.currentThread().getName() + " started");
            System.out.println("call countBarrier.count() in " + Thread.currentThread().getName());
            countBarrier.count();
        };
        Thread thread1 = new Thread(threadTask);
        Thread thread2 = new Thread(threadTask);
        thread1.start();
        thread2.start();
        countBarrier.await();
        System.out.println("Main thread ended");
    }

    public void count() {
        synchronized (monitor) {
            count++;
            monitor.notifyAll();
        }
    }

    public void await() {
        synchronized (monitor) {
            try {
                while (count < total) {
                    monitor.wait();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
