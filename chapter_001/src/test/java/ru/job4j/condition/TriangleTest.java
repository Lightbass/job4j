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
        // создаем три объекта класса Point.
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(2, 0);
        // Создаем объект треугольник и передаем в него объекты точек.
        Triangle triangle = new Triangle(a, b, c);
        // Вычисляем площадь.
        double result = triangle.area();
        // Задаем ожидаемый результат.
        double expected = 2D;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, closeTo(expected, 0.1));
    }

    /**
     * Тест метода нахождения площади с двумя одинаковыми точками.
     */
    @Test
    public void whenSetTwoSamePointsThenNothing() {
        // создаем три объекта класса Point.
        Point a = new Point(0, 0);
        Point b = a;
        Point c = new Point(2, 0);
        // Создаем объект треугольник и передаем в него объекты точек.
        Triangle triangle = new Triangle(a, b, c);
        // Вычисляем площадь.
        double result = triangle.area();
        // Задаем ожидаемый результат.
        double expected = -1D;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, closeTo(expected, 0.1));
    }

    /**
     * Тест метода нахождения площади с параллельными точками.
     */
    @Test
    public void whenThreePointsOnOneLineThenNothing1() {
        // создаем три объекта класса Point.
        Point a = new Point(0, 0);
        Point b = new Point(1, 1);
        Point c = new Point(2, 2);
        // Создаем объект треугольник и передаем в него объекты точек.
        Triangle triangle = new Triangle(a, b, c);
        // Вычисляем площадь.
        double result = triangle.area();
        // Задаем ожидаемый результат.
        double expected = -1D;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, closeTo(expected, 0.1));
    }

    /**
     * Тест метода нахождения площади с параллельными точками.
     */
    @Test
    public void whenThreePointsOnOneLineThenNothing2() {
        // создаем три объекта класса Point.
        Point a = new Point(0, 2);
        Point b = new Point(1, 2);
        Point c = new Point(2, 2);
        // Создаем объект треугольник и передаем в него объекты точек.
        Triangle triangle = new Triangle(a, b, c);
        // Вычисляем площадь.
        double result = triangle.area();
        // Задаем ожидаемый результат.
        double expected = -1D;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, closeTo(expected, 0.1));
    }

    /**
     * Тест метода нахождения площади с параллельными точками.
     */
    @Test
    public void whenThreePointsOnOneLineThenNothing3() {
        // создаем три объекта класса Point.
        Point a = new Point(1, 0);
        Point b = new Point(2, 1);
        Point c = new Point(3, 2);
        // Создаем объект треугольник и передаем в него объекты точек.
        Triangle triangle = new Triangle(a, b, c);
        // Вычисляем площадь.
        double result = triangle.area();
        // Задаем ожидаемый результат.
        double expected = -1D;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, closeTo(expected, 0.1));
    }

    /**
     * Тест метода нахождения площади с параллельными точками.
     */
    @Test
    public void whenThreePointsOnOneLineThenNothing4() {
        // создаем три объекта класса Point.
        Point a = new Point(-10, 0);
        Point b = new Point(-10, 1);
        Point c = new Point(-10, 2);
        // Создаем объект треугольник и передаем в него объекты точек.
        Triangle triangle = new Triangle(a, b, c);
        // Вычисляем площадь.
        double result = triangle.area();
        // Задаем ожидаемый результат.
        double expected = -1D;
        //Проверяем результат и ожидаемое значение.
        assertThat(result, closeTo(expected, 0.1));
    }



}