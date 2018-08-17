package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс для получения итератора с четными числами.
 * @author Alexey Makarov
 * @since 15.08.18
 * @version 0.1
 */
public class EvenIt implements Iterator {
    private final int[] num;
    private int pos;

    /**
     * Метод возвращает индекс первого четного числа начиная искать от каретки.
     * @return индекс следующего четного числа.
     */
    private int stepToEven() {
        int result = -1;
        for (int i = pos; i != num.length; i++) {
            if (num[i] % 2 == 0) {
                result = i;
                break;
            }
        }
        return result;
    }

    /**
     * Конструктор инициализирует поле перебираемого массива.
     * @param num массив.
     */
    public EvenIt(int[] num) {
        this.num = num;
    }

    /**
     * Есть ли следующее четное число в массиве.
     * @return есть ли оно.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        if (stepToEven() != -1) {
            result = true;
        }
        return result;
    }

    /**
     * Метод возвращает следующее четное число ближайшее к каретке итератора.
     * @return четное число.
     */
    @Override
    public Integer next() {
        int result = stepToEven();
        if (result == -1) {
            throw new NoSuchElementException();
        } else {
            pos = result;
        }
        return num[pos++];
    }
}