package ru.job4j.loop;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * JUnit класс для теста класса Factorial.
 * @author Alexey Makarov
 * @since 31.07.2018
 * @version 0.1
 */
public class FactorialTest {
    /**
     * Тестирования метода, который находит факториал от 5
     */
    @Test
    public void thenFactorialFiveWhenReturns120() {
        Factorial f = new Factorial();
        int result = f.calc(5);
        assertThat(result, is(120));
    }
}
