package ru.job4j.chess.firuges.black;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;

/**
 *
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class QueenBlack implements Figure {
    private final Cell position;

    public QueenBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        int deltaX = source.x - dest.x;
        int deltaY = source.y - dest.y;
        Cell[] result = null;

        if (Math.abs(deltaX) == Math.abs(deltaY)) {
            result = new Cell[Math.abs(deltaX)];
            int stepX = deltaX > 0 ? -1 : 1;
            int stepY = deltaY > 0 ? -1 : 1;
            for (int index = 0; index != result.length; index++) {
                int i = source.x + stepX * (index + 1);
                int j = source.y + stepY * (index + 1);
                result[index] = Cell.values()[i * 8 + j];
            }
        } else if (source.x == dest.x) {
            result = new Cell[Math.abs(source.y - dest.y)];
            int step = source.y - dest.y > 0 ? -1 : 1;
            for (int i = 0; i != result.length; i++) {
                result[i] = Cell.values()[source.x * 8 + (source.y + (i + 1) * step)];
            }
        } else if (source.y == dest.y) {
            result = new Cell[Math.abs(source.x - dest.x)];
            int step = source.x - dest.x > 0 ? -1 : 1;
            for (int i = 0; i != result.length; i++) {
                result[i] = Cell.values()[(source.x + (i + 1) * step) * 8 + source.y];
            }
        } else {
            throw new ImpossibleMoveException("Impossible move for this figure.");
        }
        return result;
    }

    @Override
    public Figure copy(Cell dest) {
        return new QueenBlack(dest);
    }
}
