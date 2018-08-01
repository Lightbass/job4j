package ru.job4j.array;

import java.util.Arrays;

/**
 * Класс для удаления дубликатов из массива
 * @author Alexey Makarov
 * @since 01.08.18
 * @version 0.1
 */
public class ArrayDuplicate {

    /**
     * Удаление дубликатов из массива.
     * @param array входной массив.
     * @return массив без дубликатов.
     */
    public String[] remove(String[] array) {
        int count = 0;
        String temp;
        for (int i = 0; i < array.length - count; i++) {
            for (int j = i + 1; j < array.length - count; j++) {
                if (array[i] == array[j]) {
                    temp = array[array.length - count - 1];
                    array[array.length - count - 1] = array[j];
                    array[j] = temp;
                    count++;
                }
            }
        }
        return Arrays.copyOf(array, array.length - count);
    }
}
