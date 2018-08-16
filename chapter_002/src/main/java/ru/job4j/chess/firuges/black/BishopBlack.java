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
public class BishopBlack implements Figure {
    private final Cell position;

    public BishopBlack(final Cell position) {
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
        if (Math.abs(deltaX) != Math.abs(deltaY)) {
            throw new ImpossibleMoveException("Impossible move for this figure.");
        }
        Cell[] result = new Cell[Math.abs(deltaX)];
        int stepX = deltaX > 0 ? -1 : 1;
        int stepY = deltaY > 0 ? -1 : 1;
        for (int index = 0; index != result.length; index++) {
            int i = source.x + stepX * (index + 1);
            int j = source.y + stepY * (index + 1);
            result[index] = Cell.values()[i * 8 + j];
        }
        return result;
    }

    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }
}
