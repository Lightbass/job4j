package ru.job4j.tracker;

import ru.job4j.tracker.data.*;
import ru.job4j.tracker.input.*;
import ru.job4j.tracker.actions.*;

import java.util.*;

/**
 * Класс для пользовательского консольного интерфейса.
 * @author Alexey Makarov
 * @since 05.08.2018
 * @version 0.1
 */
public class StartUI {

    /**
     * Получение данных от пользователя.
     */
    private Input input;

    /**
     * Хранилище заявок.
     */
    private Tracker tracker;

    /**
     * Переход на следующую строку.
     */
    private final String nextLine = System.lineSeparator();

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.tracker = tracker;
        this.input = input;
    }

    /**
     * Инициализация интерфейса.
     */
    public void init() {
        int select;
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        int[] range = new int[menu.getActionsLentgh()];
        for (int i = 0; i != range.length; i++) {
            range[i] = i;
        }
        do {
            menu.show();
            select = input.ask("Выберите пункт меню : ", range);
            menu.select(select);
        } while (select != 6);
    }

    /**
     * Запуск программы.
     * @param args параметры.
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(), new Tracker()).init();
    }
}
