package ru.job4j.tracker.actions;


import ru.job4j.tracker.data.Tracker;
import ru.job4j.tracker.input.Input;

/**
 * Класс для действия - выход из программы.
 * @author Alexey Makarov
 * @since 07.08.2018
 * @version 0.1
 */
public class ExitProgram implements UserAction {

    private final int key;
    private final String info;

    /**
     * Конструктор инициализирует информацию о данном действии и его ключ.
     * @param key ключ
     * @param info информация.
     */
    public ExitProgram(int key, String info) {
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
     * Основной метод.
     * @param input объект типа Input
     * @param tracker объект типа Tracker
     */
    @Override
    public void execute(Input input, Tracker tracker) {
        System.out.println(LS + "------------ Выход из программы --------------" + LS);
    }

    /**
     * Информация об операции AddAction.
     * @return строка с информацией.
     */
    @Override
    public String info() {
        return info;
    }
}
