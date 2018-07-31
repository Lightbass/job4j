package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit класс для теста класса FindLoop.
 * @author Alexey Makarov
 * @since 31.07.2018
 * @version 0.1
 */
public class FindLoopTest {
    /**
     * Ищем значение 5, которое есть в массиве.
     */
    @Test
    public void whenArrayHasLengh5Then0() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 10, 3};
        int value = 5;
        int result = find.indexOf(input, value);
        int expect = 0;
        assertThat(result, is(expect));
    }

    /**
     * Ищем значение 3, которого нет в массиве.
     */
    @Test
    public void whenArrayHasLengh3ThenNothing() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 10, 2, 4, 8};
        int value = 3;
        int result = find.indexOf(input, value);
        int expect = -1;
        assertThat(result, is(expect));
    }

    /**
     * Ищем значение 10, которое есть в массиве.
     */
    @Test
    public void whenArrayHasLengh10Then3() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {1, 6, 5, 10, 3};
        int value = 10;
        int result = find.indexOf(input, value);
        int expect = 3;
        assertThat(result, is(expect));
    }
}
