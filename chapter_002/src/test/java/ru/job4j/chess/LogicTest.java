package ru.job4j.chess;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import ru.job4j.chess.exceptions.FigureNotFoundException;
import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.exceptions.OccupiedWayException;
import ru.job4j.chess.firuges.*;
import ru.job4j.chess.firuges.white.*;
import ru.job4j.chess.firuges.black.*;

/**
 * JUnit тест класса Logic.
 * @author Alexey Makarov
 * @since 09.08.18
 * @version 0.1
 */
public class LogicTest {

    @Test(expected = OccupiedWayException.class)
    public void whenFigureOnTheWayThenException() {
        Logic logic = new Logic();
        Figure bb1 = new BishopBlack(Cell.A1);
        Figure bb2 = new BishopBlack(Cell.B2);
        logic.add(bb1);
        logic.add(bb2);
        logic.move(bb1.position(), Cell.C3);
    }

    @Test(expected = FigureNotFoundException.class)
    public void whenThen() {
        Logic logic = new Logic();
        Figure bb1 = new BishopBlack(Cell.A1);
        Figure bb2 = new BishopBlack(Cell.B2);
        logic.add(bb1);
        logic.add(bb2);
        logic.move(Cell.C2, Cell.C3);
    }

}
