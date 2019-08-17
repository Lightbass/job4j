package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit класс для теста класса FindLoop.
 * @author Alexey Makarov
 * @since 0.1
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

    /**
     * Ищем значение 10, которое есть в массиве в нужном диапазоне.
     */
    @Test
    public void whenArrayHasValue10Then1() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {1, 10, 2, 3, 4};
        int value = 10;
        int result = find.indexOf(input, value, 0, 2);
        int expect = 1;
        assertThat(result, is(expect));
    }

    /**
     * Ищем значение 10, которого нет в нужном диапазоне.
     */
    @Test
    public void whenArrayHasValue10ThenNotFound() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {2, 2, 10, 3, 4};
        int value = 10;
        int result = find.indexOf(input, value, 0, 2);
        int expect = -1;
        assertThat(result, is(expect));
    }

    /**
     * Ищем значение 5, но указываем неправильный диапазон.
     */
    @Test
    public void whenArrayHasValue5ThenNotFound() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {1, 2, 3, 4, 5};
        int value = 5;
        int result = find.indexOf(input, value, -1, 2);
        int expect = -1;
        assertThat(result, is(expect));
    }

    /**
     * Ищем значение 5, но указываем неправильный диапазон.
     */
    @Test
    public void whenArrayHasValue3ThenNotFound() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {3, 3, 3, 3};
        int value = 3;
        int result = find.indexOf(input, value, 0, 5);
        int expect = -1;
        assertThat(result, is(expect));
    }

    /**
     * Сортируем выборкой массив из 3 чисел
     */
    @Test
    public void whenArrayLengthEquals3ThenSortOk() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 1, 3};
        input = find.sort(input);
        int[] expect = new int[] {1, 3, 5};
        assertThat(input, is(expect));
    }

    /**
     * Сортируем выборкой массив из 5 чисел
     */
    @Test
    public void whenArrayLengthEquals5ThenSortOk() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {0, -1, 5, 1, 3};
        input = find.sort(input);
        int[] expect = new int[] {-1, 0, 1, 3, 5};
        assertThat(input, is(expect));
    }

    /**
     * Сортируем пустой массив.
     */
    @Test
    public void whenEmptyArrayThenSortOk() {
        FindLoop find = new FindLoop();
        int[] input = new int[0];
        input = find.sort(input);
        int[] expect = new int[0];
        assertThat(input, is(expect));
    }
}
