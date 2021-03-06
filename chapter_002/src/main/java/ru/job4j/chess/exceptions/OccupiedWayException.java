package ru.job4j.chess.exceptions;

/**
 * Исключение - клетка занята.
 * @author Alexey Makarov
 * @since 09.08.2018
 * @version 0.1
 */
public class OccupiedWayException extends RuntimeException {
    public OccupiedWayException(String msg) {
        super(msg);
    }
}
