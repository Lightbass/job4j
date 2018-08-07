package ru.job4j.tracker.actions;


import ru.job4j.tracker.data.Item;
import ru.job4j.tracker.data.Tracker;
import ru.job4j.tracker.input.Input;

/**
 * Класс для действия - отображение всех заявок.
 * @author Alexey Makarov
 * @since 07.08.2018
 * @version 0.1
 */
public class ShowItems implements UserAction {

    private final int key;
    private final String info;

    /**
     * Конструктор инициализирует информацию о данном действии и его ключ.
     * @param key ключ
     * @param info информация.
     */
    public ShowItems(int key, String info) {
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
        System.out.println(LS + "------------ Заявки в трекере --------------" + LS);
        Item[] items = tracker.findAll();
        for (Item i : items) {
            System.out.println(i + LS);
        }
        System.out.println("------------ Перечисление завершено --------------");
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
