package ru.job4j.count;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Класс - счетчик.
 * @author Alexey Makarov
 * @since 05.09.18
 * @version 0.1
 */
@ThreadSafe
public class Count {
    @GuardedBy("this")
    private int value;

    /**
     * Метод инкрементирует значение счетчика на 1.
     */
    public synchronized void increment() {
        this.value++;
    }

    /**
     * Метод возвращает значение счетчика.
     * @return значение счетчика.
     */
    public synchronized int get() {
        return this.value;
    }
}
