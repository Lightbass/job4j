package ru.job4j.chess.exceptions;

/**
 * Исключение - неправильный ход фигуры.
 * @author Alexey Makarov
 * @since 09.08.2018
 * @version 0.1
 */
public class ImpossibleMoveException extends RuntimeException {
    public ImpossibleMoveException(String msg) {
        super(msg);
    }
}
