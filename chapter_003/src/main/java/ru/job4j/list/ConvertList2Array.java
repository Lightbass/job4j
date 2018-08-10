package ru.job4j.list;

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
        int cells = list.size() / rows;
        if (list.size() % rows != 0) {
            cells++;
        }
        int[][] array = new int[rows][cells];
        int n = 0;
        int m = 0;
        for (Integer i : list) {
            array[n][m] = i;
            m++;
            if (m == cells) {
                n++;
                m = 0;
            }
        }
        return array;
    }

    public int[][] toArray2(List<Integer> list, int rows) {
        int cells = list.size() / rows;
        if (list.size() % rows != 0) {
            cells++;
        }
        int[][] array = new int[rows][cells];
        int count = 0;
        for (Integer i : list) {
            array[count / cells][count % cells] = i;
            count++;
        }
        return array;
    }
}
