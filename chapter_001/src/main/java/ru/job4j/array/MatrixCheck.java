package ru.job4j.array;

/**
 * Класс для нахождения заполнения в массиве true или false по диагоналям.
 * @author Alexey Makarov
 * @since 01.08.18
 * @version 0.1
 */
public class MatrixCheck {

    /**
     * Заполнен массив true или false диагонали.
     * @param data входящий массив
     * @return результат.
     */
    public boolean mono(boolean[][] data) {
        boolean result = true;
        for (int i = 0, j = data.length - 1; i < data.length - 1; i++, j--) {
            if (data[i][i] != data[i + 1][i + 1]) {
                result = false;
            }
            if (data[i][j] != data[i + 1][j - 1]) {
                result = false;
            }
        }
        return result;
    }
}
