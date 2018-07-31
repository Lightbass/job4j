package ru.job4j.array;

/**
 * Класс который заполняет массив числами возведенными в квадрат.
 * @author Alexey Makarov
 * @since 31.07.18
 * @version 0.1
 */
public class Square {

    /**
     * Метод заполняющий массив и возвращающий его.
     * @param bound количество элементов в массиве.
     * @return массив с квадратом каждого числа в массиве.
     */
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
            for (int i = 0; i < bound; i++) {
                rst[i] = (int) Math.pow(i + 1, 2);
            }
        return rst;
    }
}