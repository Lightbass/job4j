package ru.job4j.cache;

/**
 * Класс - исключение.
 * @author Alexey Makarov
 * @since 08.09.18
 * @version 0.1
 */
public class OptimisticException extends RuntimeException {
    public OptimisticException(String message) {
        super(message);
    }
}
