package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Runnable runnable = () -> { };
        Thread first = new Thread(runnable);
        Thread second = new Thread(runnable);
        printTwoThreadInfo(first, second);
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED || second.getState() != Thread.State.TERMINATED) {
            printTwoThreadInfo(first, second);
        }
        printTwoThreadInfo(first, second);
    }

    private static void printTwoThreadInfo(Thread thread1, Thread thread2) {
        System.out.println("Thread name: " + thread1.getName() + ", state: " + thread1.getState());
        System.out.println("Thread name: " + thread2.getName() + ", state: " + thread2.getState());
    }
}
