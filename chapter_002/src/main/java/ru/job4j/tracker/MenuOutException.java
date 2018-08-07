package ru.job4j.tracker;

/**
 * Исключение для класса MenuTracker при выходе за границы меню.
 * @author Alexey Makarov
 * @since 07.08.2018
 * @version 0.1
 */
public class MenuOutException extends RuntimeException {

    public MenuOutException(String message) {
        super(message);
    }
}
