package ru.job4j.loop;

/**
 * Класс для вычисления количества простых чисел.
 * @author Alexey Makarov
 * @since 0.1
 * @version 0.1
 */
public class PrimeNumber {

    /**
     * Метод вычисляет кол-во простых чисел.
     * @param finish до какого числе вести подсчет.
     * @return количество простых чисел.
     */
    public int calc(int finish) {
        int count = 0;
        for (int n = 2; n <= finish; n++) {
            for (int m = 2; m <= n; m++) {
                if (n % m == 0) {
                    if (m == n) {
                        count++;
                    }
                    break;
                }
            }
        }
        return count;
    }
}
