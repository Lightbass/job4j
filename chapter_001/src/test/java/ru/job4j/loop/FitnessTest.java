package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit класс для теста класса Fitness.
 * @author Alexey Makarov
 * @since 05.08.2019
 * @version 0.1
 */
public class FitnessTest {

    /**
     * Тестирование метода, когда тяга Николая больше тяги Ивана.
     */
    @Test
    public void thenNikGreaterThanIvan() {
        Fitness fitness = new Fitness();
        int result = fitness.calc(50, 450);
        assertThat(result, is(6));
    }

    /**
     * Тестирование метода, когда тяга Ивана больше тяги Николая.
     */
    @Test
    public void thenIvanGreaterThanNik() {
        Fitness fitness = new Fitness();
        int result = fitness.calc(30, 20);
        assertThat(result, is(0));
    }

    /**
     * Тестирование метода, когда тяга Ивана такая же как у Николая.
     */
    @Test
    public void thenIvanEqualsNik() {
        Fitness fitness = new Fitness();
        int result = fitness.calc(30, 30);
        assertThat(result, is(1));
    }
}
