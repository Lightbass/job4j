package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit класс для теста класса MatrixCheck.
 * @author Alexey Makarov
 * @since 01.08.2018
 * @version 0.1
 */
public class MatrixCheckTest {

    /**
     * Проверка массива 3х3 с true по диагоналям.
     */
    @Test
    public void when3x3MatrixDiagonalTrueWhenTrue() {
        boolean[][] input = {
                {true, true, true},
                {false, true, true},
                {true, false, true}
        };
        boolean answer = true;
        MatrixCheck mc = new MatrixCheck();
        assertThat(mc.mono(input), is(answer));
    }

    /**
     * Проверка массива 4х4 с false по диагоналям.
     */
    @Test
    public void when4x4MatrixDiagonalFalseWhenTrue() {
        boolean[][] input = {
                {false, true, true, false},
                {true, false, false, true},
                {true, false, false, true},
                {false, true, true, false}
        };
        boolean answer = true;
        MatrixCheck mc = new MatrixCheck();
        assertThat(mc.mono(input), is(answer));
    }

    /**
     * Проверка массива 4х4 с true и false по диагоналям.
     */
    @Test
    public void when4x4MatrixDiagonalTrueAndFalseWhenTrue() {
        boolean[][] input = {
                {true, true, true, false},
                {false, true, false, true},
                {true, false, true, false},
                {false, true, false, true}
        };
        boolean answer = true;
        MatrixCheck mc = new MatrixCheck();
        assertThat(mc.mono(input), is(answer));
    }

    /**
     * Проверка массива 3х3 с true по диагоналям и false в центре.
     */
    @Test
    public void when3x3MatrixDiagonalTrueWithOneFalseWhenFalse() {
        boolean[][] input = {
                {true, true, true},
                {false, false, true},
                {true, false, true}
        };
        boolean answer = false;
        MatrixCheck mc = new MatrixCheck();
        assertThat(mc.mono(input), is(answer));
    }
}
