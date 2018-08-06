package ru.job4j.pseudo;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса Paint.
 * @author Alexey Makarov
 * @since 03.08.18
 * @version 0.1
 */
public class PaintTest {

    /**
     * Тест рисования квадрата.
     */
    @Test
    public void whenDrawSquare() {
        String ls = System.lineSeparator();
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square());
        assertThat(
                new String(out.toByteArray()),
                is(new StringBuilder()
                                .append("+++++").append(ls)
                                .append("+   +").append(ls)
                                .append("+   +").append(ls)
                                .append("+   +").append(ls)
                                .append("+++++").append(ls)
                                .toString()
                )
        );
        System.setOut(stdout);
    }

    /**
     * Тест рисования треугольника.
     */
    @Test
    public void whenDrawTriangle() {
        String ls = System.lineSeparator();
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Triangle());
        assertThat(
                new String(out.toByteArray()),
                is(new StringBuilder()
                        .append("    +    ").append(ls)
                        .append("   +++   ").append(ls)
                        .append("  +++++  ").append(ls)
                        .append(" +++++++ ").append(ls)
                        .append("+++++++++").append(ls)
                        .toString()
                )
        );
        System.setOut(stdout);
    }
}
