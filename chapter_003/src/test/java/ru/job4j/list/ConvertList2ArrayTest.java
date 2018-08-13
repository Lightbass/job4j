package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса ConvertList2ArrayTest.
 * @author Alexey Makarov
 * @since 10.08.18
 * @version 0.1
 */
public class ConvertList2ArrayTest {
    /**
     * Не до конца заполненный массив.
     */
    @Test
    public void when7ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }

    /**
     * Почти до конца заполненный массив.
     */
    @Test
    public void when23ElementsThen24() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9,
                        10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                        20, 21, 22, 23),
                3
        );
        int[][] expect = {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {9, 10, 11, 12, 13, 14, 15, 16},
                {17, 18, 19, 20, 21, 22, 23, 0}
        };
        assertThat(result, is(expect));
    }

    /**
     * До конца заполненный массив.
     */
    @Test
    public void when24ElementsThen24() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9,
                        10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                        20, 21, 22, 23, 24),
                3
        );
        int[][] expect = {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {9, 10, 11, 12, 13, 14, 15, 16},
                {17, 18, 19, 20, 21, 22, 23, 24}
        };
        assertThat(result, is(expect));
    }

    /**
     * Не до конца заполненный массив.
     */
    @Test
    public void when7ElementsThen9Array2() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray2(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }

    /**
     * Почти до конца заполненный массив.
     */
    @Test
    public void when23ElementsThen24Array2() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray2(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9,
                        10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                        20, 21, 22, 23),
                3
        );
        int[][] expect = {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {9, 10, 11, 12, 13, 14, 15, 16},
                {17, 18, 19, 20, 21, 22, 23, 0}
        };
        assertThat(result, is(expect));
    }

    /**
     * До конца заполненный массив.
     */
    @Test
    public void when24ElementsThen24Array2() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray2(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9,
                        10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                        20, 21, 22, 23, 24),
                3
        );
        int[][] expect = {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {9, 10, 11, 12, 13, 14, 15, 16},
                {17, 18, 19, 20, 21, 22, 23, 24}
        };
        assertThat(result, is(expect));
    }

    /**
     * Проверка конвертации списка с массивами.
     */
    @Test
    public void when4ArraysInListThenListWith14Elements() {
        ConvertList2Array list = new ConvertList2Array();
        int[] a1 = {123, 45, 324};
        int[] a2 = {1, 2, 3, 4};
        int[] a3 = {5};
        int[] a4 = {5, 4, 1, 3, 5, 6};
        List<int[]> full = new ArrayList();
        full.add(a1);
        full.add(a2);
        full.add(a3);
        full.add(a4);
        Integer[] result = {123, 45, 324, 1, 2, 3, 4, 5, 5, 4, 1, 3, 5, 6};
        List<Integer> expect = Arrays.asList(result);
        assertThat(list.convert(full), is(expect));
    }
}