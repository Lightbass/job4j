package ru.job4j.list;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса ConvertMatrix2ListTest.
 * @author Alexey Makarov
 * @since 10.08.18
 * @version 0.1
 */
public class ConvertMatrix2ListTest {

    /**
     * Конвертация массива 2х2 в список.
     */
    @Test
    public void when2on2ArrayThenList4() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        int[][] input = {
                {1, 2},
                {3, 4}
        };
        List<Integer> expect = Arrays.asList(
                1, 2, 3, 4
        );
        List<Integer> result = list.toList(input);
        assertThat(result, is(expect));
    }

    /**
     * Конвертация массива 5,2,4 в список.
     */
    @Test
    public void whenRandomArrayThenList11() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        int[][] input = {
                {1, 2, 3, 56, 34},
                {3, 4},
                {12, 35, 23, 12}
        };
        List<Integer> expect = Arrays.asList(
                1, 2, 3, 56, 34, 3, 4, 12, 35, 23, 12
        );
        List<Integer> result = list.toList(input);
        assertThat(result, is(expect));
    }
}