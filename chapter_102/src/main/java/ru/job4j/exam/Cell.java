package ru.job4j.exam;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс - клетка на поле.
 * @author Alexey Makarov
 * @since 10.09.18
 * @version 0.1
 */
public class Cell {
    private final int x;
    private final int y;
    private final ReentrantLock lock = new ReentrantLock();

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ReentrantLock getLock() {
        return lock;
    }
}
