package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit класс для теста класса PrimeNumber.
 * @author Alexey Makarov
 * @since 0.1
 * @version 0.1
 */
public class PrimeNumberTest {

    /**
     * Тестирование метода, когда считаем простые числа до 50.
     */
    @Test
    public void thenFinalEquals50When15() {
        PrimeNumber primeNumber = new PrimeNumber();
        int result = primeNumber.calc(50);
        assertThat(result, is(15));
    }

    /**
     * Тестирование метода, когда считаем простые числа до 2.
     */
    @Test
    public void thenFinalEquals2When1() {
        PrimeNumber primeNumber = new PrimeNumber();
        int result = primeNumber.calc(2);
        assertThat(result, is(1));
    }

    /**
     * Тестирование метода, когда считаем простые числа до 1.
     */
    @Test
    public void thenFinalEquals1When0() {
        PrimeNumber primeNumber = new PrimeNumber();
        int result = primeNumber.calc(1);
        assertThat(result, is(0));
    }
}
