package ru.job4j.exam;

/**
 * Класс - главный герой.
 * @author Alexey Makarov
 * @since 10.09.18
 * @version 0.1
 */
public class Hero {
    private final Board board;
    private Cell cell;

    /**
     * Конструктор инициализирует поля игрового поля, координат x и y, где изначально стоит герой.
     * @param board игровое поле.
     * @param x координата х.
     * @param y координата y.
     */
    public Hero(Board board, int x, int y) {
        this.board = board;
        this.cell = board.getCell(x, y);
        this.cell.getLock().lock();
    }

    /**
     * Двигает героя на дельта X или дельта Y, предпочтительнее указать единицу в какую-то одну из сторон.
     * @param x дельта x координата.
     * @param y дельта y координата.
     * @return {@code true}, если получилось осуществить ход; {@code false}, если не удалось этого сделать.
     */
    public boolean move(int x, int y, long timeOut) {
        boolean result = false;
        Cell nextMove = board.getCell(cell.getX() + x, cell.getY() + y);
        if (nextMove != null && board.move(cell, nextMove, timeOut)) {
            result = true;
            cell = nextMove;
        }
        return result;
    }

    /**
     * Метод возвращает текущую клетку, на которой стоит персонаж.
     * @return текущая клетка.
     */
    public Cell getCurrentCell() {
        return cell;
    }
}
