package ru.job4j.max;

/**
 * Нахождение максимума.
 * @author Alexey Makarov
 * @since 31.07.18
 * @version 0.1
 */
public class Max {
    /**
     * Нахождение максимального числа из двух.
     * @param first первое число.
     * @param second второе число.
     * @return
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }

    /**
     * Нахождение максимального числа из трех.
     * @param first первое число.
     * @param second второе число.
     * @param third второе число.
     * @return
     */
    public int max(int first, int second, int third) {
        return max(max(first, second), third);
    }
}
