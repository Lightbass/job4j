package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit класс для тестирования методов класса Copy.
 * @author Alexey Makarov
 * @since 29.07.2018
 * @version 0.1
 */
public class CopyTest {

    /**
     * Тестирование метода copySortAsc 1.
     */
    @Test
    public void whenAscSortAAndSortB() {
        int[] a = {1, 3, 7, 10, 20, 30, 80};
        int[] b = {2, 2, 5, 8, 10, 15, 25};
        int[] result = {1, 2, 2, 3, 5, 7, 8, 10, 10, 15, 20, 25, 30, 80};
        Copy c = new Copy();
        assertThat(c.copySortAsc(a, b), is(result));
    }

    /**
     * Тестирование метода copySortAsc 2.
     */
    @Test
    public void whenAscSortAAndSortB2() {
        int[] a = {4, 8, 12};
        int[] b = {0, 3, 7, 10, 20, 30, 80};
        int[] result = {0, 3, 4, 7, 8, 10, 12, 20, 30, 80};
        Copy c = new Copy();
        assertThat(c.copySortAsc(a, b), is(result));
    }

    /**
     * Тестирование метода copySortDesc 1.
     */
    @Test
    public void whenDescSortAAndSortB() {
        int[] a = {100, 70, 65, 50};
        int[] b = {110, 90, 50, 25, 10, 5, 1};
        int[] result = {110, 100, 90, 70, 65, 50, 50, 25, 10, 5, 1};
        Copy c = new Copy();
        assertThat(c.copySortDesc(a, b), is(result));
    }

    /**
     * Тестирование метода copySortDesc 2.
     */
    @Test
    public void whenDescSortAAndSortB2() {
        int[] a = {100, 83, 77, 60, 50, 33, 20, 10};
        int[] b = {110, 50, 40};
        int[] result = {110, 100, 83, 77, 60, 50, 50, 40, 33, 20, 10};
        Copy c = new Copy();
        assertThat(c.copySortDesc(a, b), is(result));
    }
}
