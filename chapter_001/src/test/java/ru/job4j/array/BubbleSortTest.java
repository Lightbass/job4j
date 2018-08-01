package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * JUnit класс для теста класса ArrayChar.
 * @author Alexey Makarov
 * @since 01.08.2018
 * @version 0.1
 */
public class BubbleSortTest {

    /**
     * Шесть элементов в массиве.
     */
    @Test
    public void whenSixElementsInArray() {
        int[] input = {6, 2, 46, 10, 18, 70};
        int[] answer = {2, 6, 10, 18, 46, 70};
        BubbleSort bs = new BubbleSort();
        assertThat(bs.sort(input), is(answer));
    }
}
