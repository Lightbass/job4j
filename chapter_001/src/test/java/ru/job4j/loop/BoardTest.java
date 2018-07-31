package ru.job4j.loop;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * JUnit класс для теста класса Board.
 * @author Alexey Makarov
 * @since 31.07.2018
 * @version 0.1
 */
public class BoardTest {
    /**
     * Проверка поля 3х3.
     */
    @Test
    public void when3x3() {
        Board board = new Board();
        String rsl = board.paint(3, 3);
        String ln = System.lineSeparator();
        assertThat(rsl, is(
                String.format("X X%s X %sX X%s", ln, ln, ln)
                )
        );
    }

    /**
     * Проверка поля 5х5.
     */
    @Test
    public void when5x5() {
        Board board = new Board();
        String rsl = board.paint(5, 5);
        String ln = System.lineSeparator();
        assertThat(rsl, is(
                String.format("X X X%s X X %sX X X%s X X %sX X X%s", ln, ln, ln, ln, ln, ln)
                )
        );
    }
}
