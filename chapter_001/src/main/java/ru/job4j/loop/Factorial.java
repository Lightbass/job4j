package ru.job4j.loop;

/**
 * Класс для нахождения факториала.
 * @author Alexey Makarov
 * @since 31.07.18
 * @version 0.1
 */
public class Factorial {

    /**
     * Нахождение факториала
     * @param n число от которого берется факториал.
     * @return факториал.
     */
    public int calc(int n) {
        int sum = 1;
        while (n > 1) {
            sum *= n;
            n--;
        }
        return sum;
    }
}
