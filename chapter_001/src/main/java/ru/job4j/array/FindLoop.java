package ru.job4j.array;


/**
 * Класс для нахождения числа перебором.
 * @author Alexey Makarov
 * @since 31.07.18
 * @version 0.1
 */

public class FindLoop {

    /**
     * Метод, который возврашает индекс числа в заданном массиве.
     * @param data массив в котором производится поиск.
     * @param el число, которое требуется найти.
     * @return индекс найденного числа.
     */
    public int indexOf(int[] data, int el) {
        int rst = -1; // если элемента нет в массиве, то возвращаем -1.
        for (int index = 0; index < data.length; index++) {
            if (data[index] == el) {
                rst = index;
                break;
            }
        }
        return rst;
    }
}