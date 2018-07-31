package ru.job4j.loop;

/**
 * Класс для нахождения суммы четных чисел в заданном диапазоне.
 * @author Alexey Makarov
 * @since 31.07.18
 * @version 0.1
 */
public class Even {

    /**
     * Метод возвращает сумму четных чисел.
     * @param start начальное число.
     * @param finish конечное число.
     * @return
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int n = start; n <= finish; n++) {
            if (n % 2 == 0) {
                sum += n;
            }
        }
        return sum;
    }
}
