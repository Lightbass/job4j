package ru.job4j.condition;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

/**
 * JUnit класс для теста класса Triangle.
 * @author Alexey Makarov
 * @since 31.07.2018
 * @version 0.1
 */
public class TriangleTest {

    /**
     * Тест метода нахождения площади с нормальными данными.
     */
    @Test
    public void whenAreaSetThreePointsThenTriangleArea() {

        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(2, 0);

        Triangle triangle = new Triangle(a, b, c);

        double result = triangle.area();

        double expected = 2D;

        assertThat(result, closeTo(expected, 0.1));
    }

    /**
     * Тест метода нахождения площади с двумя одинаковыми точками.
     */
    @Test
    public void whenSetTwoSamePointsThenNothing() {

        Point a = new Point(0, 0);
        Point b = a;
        Point c = new Point(2, 0);

        Triangle triangle = new Triangle(a, b, c);

        double result = triangle.area();

        double expected = -1D;

        assertThat(result, closeTo(expected, 0.1));
    }

    /**
     * Тест метода нахождения площади с параллельными точками.
     */
    @Test
    public void whenThreePointsOnOneLineThenNothing1() {

        Point a = new Point(0, 0);
        Point b = new Point(1, 1);
        Point c = new Point(2, 2);

        Triangle triangle = new Triangle(a, b, c);

        double result = triangle.area();

        double expected = -1D;

        assertThat(result, closeTo(expected, 0.1));
    }

    /**
     * Тест метода нахождения площади с параллельными точками.
     */
    @Test
    public void whenThreePointsOnOneLineThenNothing2() {

        Point a = new Point(0, 2);
        Point b = new Point(1, 2);
        Point c = new Point(2, 2);

        Triangle triangle = new Triangle(a, b, c);

        double result = triangle.area();

        double expected = -1D;

        assertThat(result, closeTo(expected, 0.1));
    }

    /**
     * Тест метода нахождения площади с параллельными точками.
     */
    @Test
    public void whenThreePointsOnOneLineThenNothing3() {

        Point a = new Point(1, 0);
        Point b = new Point(2, 1);
        Point c = new Point(3, 2);

        Triangle triangle = new Triangle(a, b, c);

        double result = triangle.area();

        double expected = -1D;

        assertThat(result, closeTo(expected, 0.1));
    }

    /**
     * Тест метода нахождения площади с параллельными точками.
     */
    @Test
    public void whenThreePointsOnOneLineThenNothing4() {

        Point a = new Point(-10, 0);
        Point b = new Point(-10, 1);
        Point c = new Point(-10, 2);

        Triangle triangle = new Triangle(a, b, c);

        double result = triangle.area();

        double expected = -1D;

        assertThat(result, closeTo(expected, 0.1));
    }
}
