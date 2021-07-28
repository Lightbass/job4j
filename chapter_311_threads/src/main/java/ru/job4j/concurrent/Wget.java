package ru.job4j.concurrent;

import java.util.stream.IntStream;

public class Wget {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            IntStream.rangeClosed(0, 100)
                    .forEach(progress -> {
                        System.out.print("\rLoading: " + progress + "%");
                        simulationOfWork();
                    });
            System.out.print("\rLoaded.");
        });
        thread.start();
    }

    private static void simulationOfWork() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
