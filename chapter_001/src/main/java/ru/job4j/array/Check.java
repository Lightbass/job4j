package ru.job4j.array;

/**
 * Класс для проверки булевого значения в каждом элементе массива.
 * @author Alexey Makarov
 * @since 01.08.18
 * @version 0.1
 */
public class Check {

    /**
     * Проверка, что в каждом элементе массива одинаковое значение boolean.
     * @param data массив
     * @return одинаковые элементы или нет.
     */
    public boolean mono(boolean[] data) {
        boolean result = true;
        for (int i = 0; i < data.length - 1; i++) {
            if (data[i] != data[i + 1]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
