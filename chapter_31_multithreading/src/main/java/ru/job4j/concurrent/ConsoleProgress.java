package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
    }

    @Override
    public void run() {
        char[] process = {'-', '\\', '|', '/'};
        int count = 0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\r Loading: " + process[count]);
            count = count == process.length - 1 ? 0 : count + 1;
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.print("\r Loaded");
    }
}
