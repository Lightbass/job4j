package ru.job4j.tracker.actions;


import ru.job4j.tracker.data.Item;
import ru.job4j.tracker.data.Tracker;
import ru.job4j.tracker.input.Input;

/**
 * Класс для действия - поиск заявки по идентификатору.
 * @author Alexey Makarov
 * @since 07.08.2018
 * @version 0.1
 */
public class FindItemById implements UserAction {

    private final int key;
    private final String info;

    /**
     * Конструктор инициализирует информацию о данном действии и его ключ.
     * @param key ключ
     * @param info информация.
     */
    public FindItemById(int key, String info) {
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
        System.out.println(LS + "------------ Поиск заявки по идентификатору --------------" + LS);
        Item item = checkStrGetItem(input.ask("Введите идентификатор(ID) заявки : "), tracker);
        if (item != null) {
            System.out.println(LS + item + LS + LS + "------------ Найдена заявка -----------");
        }
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
