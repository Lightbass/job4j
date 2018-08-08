package ru.job4j.tracker.actions;


/**
 * Базовый класс с методами по умолчанию.
 * @author Alexey Makarov
 * @since 07.08.2018
 * @version 0.1
 */
public abstract class BaseAction implements UserAction {

    /**
     * Ключ - идентификатор действия.
     */
    private final int key;

    /**
     * Информация о действии.
     */
    private final String info;

    /**
     * Конструктор инициализирует информацию о данном действии и его ключ.
     * @param key ключ
     * @param info информация.
     */
    protected BaseAction(int key, String info) {
        this.key = key;
        this.info = info;
    }

    /**
     * Метод возвращает ключ опции.
     * @return ключ
     */
    @Override
    public int key() {
        return key;
    }

    /**
     * Информация об операции AddAction.
     * @return строка с информацией.
     */
    @Override
    public String info() {
        return String.format("%d. %s", this.key(), info);
    }
}
