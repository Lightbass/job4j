package ru.job4j.loop;

import org.junit.Test;
import java.util.StringJoiner;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * JUnit класс для теста класса Paint.
 * @author Alexey Makarov
 * @since 31.07.2018
 * @version 0.1
 */
public class PaintTest {

    /**
     * Тест пирамиды слева.
     */
    @Test
    public void whenPyramid4Left() {
        Paint paint = new Paint('^', ' ');
        String rst = paint.leftTrl(4);
        assertThat(rst,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("   ^")
                                .add("  ^^")
                                .add(" ^^^")
                                .add("^^^^")
                                .toString()
                )
        );
    }

    /**
     * Тест пирамиды справа.
     */
    @Test
    public void whenPyramid4Right() {
        Paint paint = new Paint('^', ' ');
        String rst = paint.rightTrl(4);
        assertThat(rst,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("^   ")
                                .add("^^  ")
                                .add("^^^ ")
                                .add("^^^^")
                                .toString()
                )
        );
    }

    /**
     * Проверка пирамиды высотой 4.
     */
    @Test
    public void whenPyramidFull4() {
        Paint paint = new Paint('^', ' ');
        String rst = paint.pyramid(4);
        assertThat(rst,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("   ^   ")
                                .add("  ^^^  ")
                                .add(" ^^^^^ ")
                                .add("^^^^^^^")
                                .toString()
                )
        );
    }

    /**
     * Проверка пирамиды высотой  7.
     */
    @Test
    public void whenPyramidFull7() {
        Paint paint = new Paint('^', ' ');
        String rst = paint.pyramid(7);
        assertThat(rst,
                is(
                        new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                                .add("      ^      ")
                                .add("     ^^^     ")
                                .add("    ^^^^^    ")
                                .add("   ^^^^^^^   ")
                                .add("  ^^^^^^^^^  ")
                                .add(" ^^^^^^^^^^^ ")
                                .add("^^^^^^^^^^^^^")
                                .toString()
                )
        );
    }
}

