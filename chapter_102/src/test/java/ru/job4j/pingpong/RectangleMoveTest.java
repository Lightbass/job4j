package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса RectangleMove.
 * @author Alexey Makarov
 * @since 20.08.18
 * @version 0.1
 */
public class RectangleMoveTest {

    @Test
    public void whenRectangleMovesThenOk() {
        int square = 50;
        Rectangle rect = new Rectangle(10, 10);
        rect.setX(5);
        rect.setY(10);
        RectangleMove rm = new RectangleMove(rect, square, square);
        for (int i = 0; i < 10; i++) {
            rm.moveFigure();
        }
        assertThat(rect.getX() == 15, is(true));
        assertThat(rect.getY() == 20, is(true));
        for (int i = 0; i < 45; i++) {
            rm.moveFigure();
        }
        assertThat(rect.getX() == 40, is(true));
        assertThat(rect.getY() == 35, is(true));
        for (int i = 0; i < 45; i++) {
            rm.moveFigure();
        }
        assertThat(rect.getX() == 5, is(true));
        assertThat(rect.getY() == 10, is(true));
    }
}
