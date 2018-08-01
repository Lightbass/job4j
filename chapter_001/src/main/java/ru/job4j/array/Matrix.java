package ru.job4j.array;

/**
 * Класс для генерации таблицы умножения.
 * @author Alexey Makarov
 * @since 01.08.18
 * @version 0.1
 */
public class Matrix {

    /**
     * Метод возвращающий таблицу умножение в массиве NxN.
     * @param size размер массива N.
     * @return заполненный массивю
     */
    public int[][] multiply(int size) {
        int[][] result = new int[size][size];
        for (int n = 0; n < size; n++) {
            for (int m = 0; m < size; m++) {
                result[n][m] = (n + 1) * (m + 1);
            }
        }
        return result;
    }
}
