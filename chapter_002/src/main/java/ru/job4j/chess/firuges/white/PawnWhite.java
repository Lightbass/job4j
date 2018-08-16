package ru.job4j.chess.firuges.white;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

import java.awt.*;
import java.util.ListIterator;

/**
 * //TODO add comments.
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class PawnWhite implements Figure {
    private final Cell position;
    private boolean longMove;

    public PawnWhite(final Cell position) {
        this(position, true);
    }

    private PawnWhite(final Cell position, boolean longMove) {
        this.longMove = longMove;
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] steps = new Cell[0];
        if (longMove && source.y == dest.y - 2 && source.x == dest.x) {
            Cell first = Cell.values()[dest.x * 8 + (dest.y - 1)];
            steps = new Cell[] {first, dest};
            longMove = false;
        } else if (source.y == dest.y - 1 && source.x == dest.x) {
            longMove = false;
            steps = new Cell[]{dest};
        } else if (!(source.x == dest.x && source.y == dest.y)) {
            throw new ImpossibleMoveException("Impossible move for this figure.");
        }
        return steps;
    }

    @Override
    public Figure copy(Cell dest) {
        return new PawnWhite(dest, longMove);
    }
}
