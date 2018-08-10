package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс - конвертирует двумерный массив в List.
 * @author Alexey Makarov
 * @since 10.08.18
 * @version 0.1
 */
public class ConvertMatrix2List {

    /**
     * Конвертация двумерного массива в список.
     * @param array двурмерный массив.
     * @return список с элементами массива.
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int[] arrayi : array) {
            for(int arrayj : arrayi) {
                list.add(arrayj);
            }
        }
        return list;
    }
}