package ru.job4j.trackerbase;

import ru.job4j.trackerbase.data.ItemContainer;
import ru.job4j.trackerbase.data.Tracker;
import ru.job4j.trackerbase.input.ConsoleInput;
import ru.job4j.trackerbase.input.Input;
import ru.job4j.trackerbase.input.ValidateInput;

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
    private ItemContainer tracker;

    /**
     * Конструтор инициализирующий поля.
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, ItemContainer tracker) {
        this.tracker = tracker;
        this.input = input;
    }

    /**
     * Инициализация интерфейса.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions();
        int[] range = menu.getActionKeys();
        do {
            menu.show();
            menu.select(input.ask("Выберите пункт меню : ", range));
        } while (!menu.isExit());
    }

    /**
     * Запуск программы.
     * @param args параметры.
     */
    public static void main(String[] args) {
        new StartUI(new ValidateInput(new ConsoleInput()), new Tracker()).init();
    }
}
