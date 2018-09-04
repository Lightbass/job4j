package ru.job4j.problem;

/**
 * Класс для отражения проблем при работе с потоками.
 * @author Alexey Makarov
 * @since 03.09.18
 * @version 0.1
 */
public class ThreadProblem implements Runnable {
    private int count;
    private Increment inc;

    public ThreadProblem(int count, Increment inc) {
        this.count = count;
        this.inc = inc;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            inc.inc();
        }
    }
}

class Increment {
    private volatile int i;
    public void inc() {
        i++;
    }
    public int get() {
        return i;
    }
}
