package ru.job4j.comparator;

import java.util.Comparator;

/**
 * Класс - сравнивает 2 строки.
 * @author Alexey Makarov
 * @since 13.08.18
 * @version 0.1
 */
public class ListCompare implements Comparator<String> {

    /**
     * Сравнивает 2 строки и выдаёт результат.
     * @param left первая строка.
     * @param right вторая строка.
     * @return результат сравнения.
     */
    @Override
    public int compare(String left, String right) {
        int result = left.length() - right.length();
        int min = Math.min(left.length(), right.length());
        for (int i = 0; i != min; i++) {
            int temp = left.charAt(i) - right.charAt(i);
            if (temp != 0) {
                result = temp;
                break;
            }
        }
        return result;
    }
}