package ru.job4j.exam;

import java.util.concurrent.TimeUnit;

/**
 * Класс - поле игры.
 * @author Alexey Makarov
 * @since 10.09.18
 * @version 0.1
 */
public class Board {
    private final int boardSize;
    private final Cell[][] cells;

    /**
     * Конструктор инициализирует поле заданной величины вставляя стены как в игре бомбермен.
     * @param boardSize размер поля.
     * @param blocked если {@code true}, то поле будет блокировано блоками расставленными как в игре бомбермен;
     *                если {@code false}, то поле будет пустым.
     */
    public Board(int boardSize, boolean blocked) {
        this.boardSize = boardSize;
        cells = new Cell[boardSize][boardSize];
        for (int i = 0; i != boardSize; i++) {
            for (int j = 0; j != boardSize; j++) {
                cells[i][j] = new Cell(i, j);
                if (blocked && (i % 2) == 1 && (j % 2) == 1) {
                    cells[i][j].getLock().lock();
                }
            }
        }
    }

    /**
     * Проверяет и устанавливает лок на клетках для дальнейшего перемещения персонажа.
     * @param source откуда двигается персонаж.
     * @param dist куда двигается персонаж.
     * @param timeOut сколько времени ждет метод, ожидая освобождения клетки.
     * @return {@code true}, если удается успешно переместить персонажа; {@code false}, если не удается.
     */
    boolean move(Cell source, Cell dist, long timeOut) {
        boolean result = false;
        try {
            if (dist.getLock().tryLock(timeOut, TimeUnit.MILLISECONDS)) {
                source.getLock().unlock();
                result = true;
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        return result;
    }

    /**
     * Метод находит клетку по заданным координатам.
     * @param x координата x.
     * @param y координата y.
     * @return клетка на данных координатах.
     */
    public Cell getCell(int x, int y) {
        Cell result = null;
        if (x >= 0 && x < boardSize && y >= 0 && y < boardSize) {
            result = cells[x][y];
        }
        return result;
    }
}
