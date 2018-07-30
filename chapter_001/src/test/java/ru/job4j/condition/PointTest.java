package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;

/**
 * JUnit класс для теста класса Point.
 * @author Alexey Makarov
 * @since 30.07.18
 * @version 0.1
 */
public class PointTest {

    /**
     * Тест метода distanceTo().
     */
    @Test
    public void whenFindDistanceOnPoints() {
        Point p1 = new Point(2, 2);
        Point p2 = new Point(6, 6);
        double result = p1.distanceTo(p2);
        assertThat(result, closeTo(5.65, 0.1));
    }
}
