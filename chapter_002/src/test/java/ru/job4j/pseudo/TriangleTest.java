package ru.job4j.pseudo;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса Triangle.
 * @author Alexey Makarov
 * @since 03.08.18
 * @version 0.1
 */
public class TriangleTest {

    /**
     * Тест рисования треугольника.
     */
    @Test
    public void whenDrawTriangle() {
        Triangle triangle = new Triangle();
        String ls = System.lineSeparator();
        assertThat(
                triangle.draw(),
                is(new StringBuilder()
                                .append("    +    ").append(ls)
                                .append("   +++   ").append(ls)
                                .append("  +++++  ").append(ls)
                                .append(" +++++++ ").append(ls)
                                .append("+++++++++").append(ls)
                                .toString()
                )
        );
    }
}
