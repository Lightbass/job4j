package ru.job4j.chess;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.*;
import ru.job4j.chess.firuges.white.*;
import ru.job4j.chess.firuges.black.*;

/**
 * JUnit тест классов Figure.
 * @author Alexey Makarov
 * @since 09.08.18
 * @version 0.1
 */
public class FigureTest {

    @Test
    public void whenPawnWhiteMoveThenOK() {
        Figure figure = new PawnWhite(Cell.A2);
        assertThat(figure.way(Cell.A2, Cell.A4), is(new Cell[]{Cell.A3, Cell.A4}));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenPawnWhiteMoveThenException() {
        Figure figure = new PawnWhite(Cell.A2);
        figure.way(Cell.A2, Cell.A5);
    }

    @Test
    public void whenPawnBlackMoveThenOK() {
        Figure figure = new PawnBlack(Cell.A7);
        assertThat(figure.way(Cell.A7, Cell.A5), is(new Cell[]{Cell.A6, Cell.A5}));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenPawnBlackMoveThenException() {
        Figure figure = new PawnBlack(Cell.A7);
        figure.way(Cell.A7, Cell.A4);
    }

    @Test
    public void whenBishopWhiteMoveThenOK() {
        Figure figure = new BishopWhite(Cell.A2);
        assertThat(figure.way(Cell.A2, Cell.C4), is(new Cell[]{Cell.B3, Cell.C4}));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenBishopWhiteMoveThenException() {
        Figure figure = new BishopWhite(Cell.A2);
        figure.way(Cell.A2, Cell.C5);
    }

    @Test
    public void whenKingWhiteMoveThenOK() {
        Figure figure = new KingWhite(Cell.A2);
        assertThat(figure.way(Cell.A2, Cell.A3), is(new Cell[]{Cell.A3}));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenKingWhiteMoveThenException() {
        Figure figure = new KingWhite(Cell.A2);
        figure.way(Cell.A2, Cell.A4);
    }

    @Test
    public void whenKnightWhiteMoveThenOK() {
        Figure figure = new KnightWhite(Cell.A2);
        assertThat(figure.way(Cell.A2, Cell.B4), is(new Cell[]{Cell.B4}));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenKnightWhiteMoveThenException() {
        Figure figure = new KnightWhite(Cell.A2);
        figure.way(Cell.A2, Cell.B5);
    }

    @Test
    public void whenQueenWhiteMoveThenOK() {
        Figure figure = new QueenWhite(Cell.A2);
        assertThat(figure.way(Cell.A2, Cell.C4), is(new Cell[]{Cell.B3, Cell.C4}));
        assertThat(figure.way(Cell.C4, Cell.C7), is(new Cell[]{Cell.C5, Cell.C6, Cell.C7}));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenQueenWhiteMoveThenException() {
        Figure figure = new QueenWhite(Cell.A2);
        figure.way(Cell.A2, Cell.C5);
    }

    @Test
    public void whenRookWhiteMoveThenOK() {
        Figure figure = new QueenWhite(Cell.C4);
        assertThat(figure.way(Cell.C4, Cell.C7), is(new Cell[]{Cell.C5, Cell.C6, Cell.C7}));
    }

    @Test(expected = ImpossibleMoveException.class)
    public void whenRookWhiteMoveThenException() {
        Figure figure = new QueenWhite(Cell.C4);
        figure.way(Cell.C4, Cell.D7);
    }

}
