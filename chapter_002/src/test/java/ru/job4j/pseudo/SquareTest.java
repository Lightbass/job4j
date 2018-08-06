package ru.job4j.pseudo;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса Square.
 * @author Alexey Makarov
 * @since 03.08.18
 * @version 0.1
 */
public class SquareTest {

    /**
     * Тест рисования квадрата.
     */
    @Test
    public void whenDrawSquare() {
        Square square = new Square();
        String ls = System.lineSeparator();
        assertThat(
                square.draw(),
                is(new StringBuilder()
                                .append("+++++").append(ls)
                                .append("+   +").append(ls)
                                .append("+   +").append(ls)
                                .append("+   +").append(ls)
                                .append("+++++").append(ls)
                                .toString()
                )
        );
    }
}
