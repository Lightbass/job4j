package ru.job4j.pseudo;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

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
     * Поле содержит дефолтный вывод в консоль.
     */
    private final PrintStream stdout = System.out;

    /**
     * Буфер для результата.
     */
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Родной для текущей ОС переход на следующую строку.
     */
    private final String ls = System.lineSeparator();

    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    /**
     * Тест рисования квадрата.
     */
    @Test
    public void whenDrawSquare() {
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
        new Paint().draw(new Triangle());
        assertThat(
                new String(out.toByteArray()),
                is(new StringBuilder()
                        .append("    +    ").append(ls)
                        .append("   + +   ").append(ls)
                        .append("  +   +  ").append(ls)
                        .append(" +     + ").append(ls)
                        .append("+++++++++").append(ls)
                        .toString()
                )
        );
        System.setOut(stdout);
    }
}
