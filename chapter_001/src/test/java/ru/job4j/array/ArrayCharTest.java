package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit класс для теста класса ArrayChar.
 * @author Alexey Makarov
 * @since 01.08.2018
 * @version 0.1
 */
public class ArrayCharTest {

    /**
     * Когда префикс есть в строке.
     */
    @Test
    public void whenStartWithPrefixThenTrue() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("He");
        assertThat(result, is(true));
    }

    /**
     * Когда префикса нет в строке.
     */
    @Test
    public void whenNotStartWithPrefixThenFalse() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("Hi");
        assertThat(result, is(false));
    }

    /**
     * Когда префикс длиннее, чем строка в которой идет поиск.
     */
    @Test
    public void whenPrefixTooLongThenFalse() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("Hellooooo");
        assertThat(result, is(false));
    }
}