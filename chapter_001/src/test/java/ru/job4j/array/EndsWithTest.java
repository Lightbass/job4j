package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit класс для теста класса EndsWith.
 * @author Alexey Makarov
 * @since 0.1
 * @version 0.1
 */
public class EndsWithTest {

    /**
     * Когда postfix есть в строке.
     */
    @Test
    public void whenEndWithPostfixThenTrue() {
        EndsWith word = new EndsWith();
        boolean result = word.endsWith("Hello", "lo");
        assertThat(result, is(true));
    }

    /**
     * Когда postfix'а нет в строке.
     */
    @Test
    public void whenNotEndWithPostfixThenFalse() {
        EndsWith word = new EndsWith();
        boolean result = word.endsWith("Hello", "la");
        assertThat(result, is(false));
    }

    /**
     * Когда постфикс длиннее слова
     */
    @Test
    public void whenPostfixLargerThanWordThenFalse() {
        EndsWith endsWith = new EndsWith();
        boolean result = endsWith.endsWith("Hello", "ohHello");
        assertThat(result, is(true));
    }
}