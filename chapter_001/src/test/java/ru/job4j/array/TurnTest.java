package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * JUnit класс для теста класса Turn.
 * @author Alexey Makarov
 * @since 01.08.2018
 * @version 0.1
 */
public class TurnTest {

    /**
     * Тест на четное кол-во элементов в массиве.
     */
    @Test
    public void whenArrayHaveFourElementsThenOk() {
        Turn turn = new Turn();
        int[] input = {5, 30, 50, 60};
        int[] result = {60, 50, 30, 5};
        assertThat(turn.turn(input), is(result));
    }

    /**
     * Тест на нечетное кол-во элементов в массиве.
     */
    @Test
    public void whenArrayHaveSevenElementsThenOk() {
        Turn turn = new Turn();
        int[] input = {5, 30, 50, 60, 34, 20, 10};
        int[] result = {10, 20, 34, 60, 50, 30, 5};
        assertThat(turn.turn(input), is(result));
    }
}
