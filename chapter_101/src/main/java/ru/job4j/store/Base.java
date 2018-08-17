package ru.job4j.store;

/**
 * Класс для моделей.
 * @author Alexey Makarov
 * @since 17.08.18
 * @version 0.1
 */
public abstract class Base {
    private final String id;

    protected Base(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}