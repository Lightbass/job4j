package ru.job4j.list;

import java.util.Arrays;
import java.util.List;

/**
 * Класс - конвертирует List в двумерный массив.
 * @author Alexey Makarov
 * @since 10.08.18
 * @version 0.1
 */
public class ConvertList2Array {

    /**
     * Возвращает двумерный массив с нужным количеством строк из элементов предоставленного списка.
     * @param list список.
     * @param rows кол-во строк.
     * @return двумерный массив.
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int cells;
        int[][] array;
        if (list.size() % rows != 0) {
            cells = list.size() / rows + 1;
            array = new int[rows][cells];
            Arrays.fill(array[rows - 1], list.size() % cells, cells, 0);
        } else {
            cells = list.size() / rows;
            array = new int[rows][cells];
        }
        int count = 0;
        for (Integer i : list) {
            array[count / cells][count % cells] = i;
            count++;
        }
        return array;
    }
}
