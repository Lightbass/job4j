package ru.job4j.chess.exceptions;

/**
 * Исключение - не найдена фигура в клетке.
 * @author Alexey Makarov
 * @since 09.08.2018
 * @version 0.1
 */
public class FigureNotFoundException extends RuntimeException {
    public FigureNotFoundException(String msg) {
        super(msg);
    }
}
