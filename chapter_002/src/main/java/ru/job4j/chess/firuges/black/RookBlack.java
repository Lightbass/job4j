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
public class RookBlack implements Figure {
    private final Cell position;

    public RookBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) {
        Cell[] result = null;
        if (source.x != dest.x && source.y != dest.y) {
            throw new ImpossibleMoveException("Impossible move for this figure.");
        } else if (source.x == dest.x) {
            result = new Cell[Math.abs(source.y - dest.y)];
            int step = source.y - dest.y > 0 ? -1 : 1;
            for (int i = 0; i != result.length; i++) {
                result[i] = Cell.valueOf(Cell.getName(source.x, source.y + (i + 1) * step));
            }
        } else if (source.y == dest.y) {
            result = new Cell[Math.abs(source.x - dest.x)];
            int step = source.x - dest.x > 0 ? -1 : 1;
            for (int i = 0; i != result.length; i++) {
                result[i] = Cell.valueOf(Cell.getName(source.x + (i + 1) * step, source.y));
            }
        }
        return result;
    }

    @Override
    public Figure copy(Cell dest) {
        return new RookBlack(dest);
    }
}
