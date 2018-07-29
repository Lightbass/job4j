package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit класс для тестирования методов класса Calculator.
 * @author Makarov Alexey
 * @since 29.07.18
 * @version 0.1
 */
public class CalculatorTest {
    /**
     * Тест метода суммирования add().
     */
    @Test
    public void whenTwoAddToThreeThenSix() {
        double x = 2D, y = 3D;
        double expect = 5.0;
        Calculator calc = new Calculator();
        calc.add(x, y);
        double result = calc.getResult();
        assertThat(result, is(expect));
    }

    /**
     * Тест метода вычитания sub().
     */
    @Test
    public void whenTwoSubToThreeThenZero() {
        double x = 2D, y = 3D;
        double expect = -1.0;
        Calculator calc = new Calculator();
        calc.sub(x, y);
        double result = calc.getResult();
        assertThat(result, is(expect));
    }

    /**
     * Тест метода умножения mul().
     */
    @Test
    public void whenTwoMulToThreeThenNine() {
        double x = 2D, y = 3D;
        double expect = 6.0;
        Calculator calc = new Calculator();
        calc.mul(x, y);
        double result = calc.getResult();
        assertThat(result, is(expect));
    }

    /**
     * Тест метода деления div().
     */
    @Test
    public void whenNineDivToThreeThenOne() {
        double x = 9D, y = 3D;
        double expect = 3.0;
        Calculator calc = new Calculator();
        calc.div(x, y);
        double result = calc.getResult();
        assertThat(result, is(expect));
    }

}
