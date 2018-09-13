package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;
import org.junit.Before;
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

    private int square = 50;
    private Rectangle rect;
    private RectangleMove rm;

    @Before
    public void prepare() {
        rect = new Rectangle(10, 10);
        rect.setX(5);
        rect.setY(10);
        rm = new RectangleMove(rect, square, square);
    }

    @Test
    public void whenRectangleMoves10TimesThenOk() {
        for (int i = 0; i < 10; i++) {
            rm.moveFigure();
        }
        assertThat(rect.getX(), is(15.0));
        assertThat(rect.getY(), is(20.0));
    }

    @Test
    public void whenRectangleMoves55TimesThenOk() {
        for (int i = 0; i < 55; i++) {
            rm.moveFigure();
        }
        assertThat(rect.getX(), is(40.0));
        assertThat(rect.getY(), is(35.0));
    }

    @Test
    public void whenRectangleMoves100TimesThenOk() {
        for (int i = 0; i < 100; i++) {
            rm.moveFigure();
        }
        assertThat(rect.getX(), is(5.0));
        assertThat(rect.getY(), is(10.0));
    }
}
