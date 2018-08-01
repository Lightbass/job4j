package ru.job4j.array;

/**
 * Класс для сортировки массива с помощью алгоритма сортировки пузырьком.
 * @author Alexey Makarov
 * @since 01.08.18
 * @version 0.1
 */
public class BubbleSort {

    /**
     * Метод сортировки массива.
     * @param array массив, который нужно отсортировать.
     * @return отсортированный массив.
     */
    int[] sort(int[] array) {
        int temp;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        return array;
    }
}
