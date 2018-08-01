package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit класс для теста класса Matrix.
 * @author Alexey Makarov
 * @since 01.08.2018
 * @version 0.1
 */
public class MatrixTest {

    /**
     * Тестирование матрицы 2х2.
     */
    @Test
    public void when2on2() {
        Matrix matrix = new Matrix();
        int[][] table = matrix.multiply(2);
        int[][] expect = {
                {1, 2},
                {2, 4}
        };
        assertThat(table, is(expect));
    }

    /**
     * Тестирование матрицы 4х4.
     */
    @Test
    public void when4on4() {
        Matrix matrix = new Matrix();
        int[][] table = matrix.multiply(4);
        int[][] expect = {
                {1, 2, 3, 4},
                {2, 4, 6, 8},
                {3, 6, 9, 12},
                {4, 8, 12, 16}
        };
        assertThat(table, is(expect));
    }
}