package ru.job4j.array;

/**
 * Класс для переворачивания массива.
 * @author Alexey Makarov
 * @since 01.08.18
 * @version 0.1
 */
public class Turn {

    /**
     * Метод для переворачивания массива.
     * @param array входной массив.
     * @return перевернутый массив.
     */
    public int[] turn(int[] array) {
        int temp;
        for (int begin = 0, end = array.length - 1; begin < array.length / 2; begin++, end--) {
            temp = array[begin];
            array[begin] = array[end];
            array[end] = temp;
        }
        return array;
    }
}
