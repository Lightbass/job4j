package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit класс для теста класса Even.
 * @author Alexey Makarov
 * @since 31.07.2018
 * @version 0.1
 */
public class CounterTest {
    /**
     * Тест вычисления суммы четных чисел, метод add.
     */
    @Test
    public void thenFromOneToTenWhenThirty() {
        Even even = new Even();
        int result = even.add(1, 10);
        assertThat(result, is(30));
    }
}
