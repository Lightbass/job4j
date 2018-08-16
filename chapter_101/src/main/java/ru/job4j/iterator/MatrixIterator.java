package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс для получения итератора из массива.
 * @author Alexey Makarov
 * @since 15.08.18
 * @version 0.1
 */
public class MatrixIterator implements Iterator {
    private int[][] mas;
    private int posi;
    private int posj;

    /**
     * Конструктор инициализирующий двумерный массив, который будет перебираться.
     * @param e двумерный массив.
     */
    public MatrixIterator(int[][] e) {
        this.mas = e;
    }

    /**
     * Метод проверяет, есть ли в итераторе следующий элемент.
     * @return есть или нет.
     */
    @Override
    public boolean hasNext() {
        boolean result = false;
        if (mas.length != 0) {
            result = true;
            while (posj == mas[posi].length) {
                if (posi != mas.length - 1) {
                    posi++;
                    posj = 0;
                } else {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * Метод возвращает следующий элемент.
     * @return элемент.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return mas[posi][posj++];
    }
}
