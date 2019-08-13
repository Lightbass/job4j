package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit класс для теста класса Mortgage.
 * @author Alexey Makarov
 * @since 0.1
 * @version 0.1
 */
public class MortGageTest {

    /**
     * Тестирование метода, когда все параметры равны 50.
     */
    @Test
    public void thenAllParams50When1() {
        Mortgage mortgage = new Mortgage();
        int result = mortgage.year(50, 50, 50);
        assertThat(result, is(1));
    }

    /**
     * Тестирование метода, когда ставка 25%, кредит на 1000, в месяц оплачивается 50.
     */
    @Test
    public void thenAmount1000Monthly50Percent25When3() {
        Mortgage mortgage = new Mortgage();
        int result = mortgage.year(1000, 50, 25);
        assertThat(result, is(3));
    }

    /**
     * Тестирование метода, когда ставка 1%, кредит на 1000, в месяц оплачивается 100.
     */
    @Test
    public void thenAmount1000Monthly100Percent1When1() {
        Mortgage mortgage = new Mortgage();
        int year = mortgage.year(1000, 100, 1);
        assertThat(year, is(1));
    }

    /**
     * Тестирование метода, когда ставка 50%, кредит на 100, в месяц оплачивается 10.
     */
    @Test
    public void thenAmount100Monthly50Percent10When2() {
        Mortgage mortgage = new Mortgage();
        int year = mortgage.year(100, 10, 50);
        assertThat(year, is(2));
    }
}
