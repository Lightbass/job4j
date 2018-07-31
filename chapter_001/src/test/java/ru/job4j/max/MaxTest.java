package ru.job4j.max;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * JUnit класс для теста класса Max.
 * @author Alexey Makarov
 * @since 31.07.18
 * @version 0.1
 */
public class MaxTest {
    /**
     * Тест метода max.
     */
    @Test
    public void whenFirstLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(1, 2);
        assertThat(result, is(2));
    }
}
