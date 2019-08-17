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

    /**
     * Метод, который возврашает индекс числа в заданном массиве в заданном диапазоне.
     * @param data массив в котором производится поиск.
     * @param el число, которое требуется найти.
     * @param start индекс с которого начинается поиск, включительно.
     * @param finish индекс до которого ведется поиск, исключительно.
     * @return индекс найденного числа.
     */
    public int indexOf(int[] data, int el, int start, int finish) {
        int rst = -1; // если элемента нет в массиве, то возвращаем -1.
        if (data.length >= finish && start < finish && start >= 0) {
            for (int index = start; index < finish; index++) {
                if (data[index] == el) {
                    rst = index;
                    break;
                }
            }
        }
        return rst;
    }
}