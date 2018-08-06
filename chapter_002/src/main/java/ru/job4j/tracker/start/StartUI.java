package ru.job4j.tracker.start;

import ru.job4j.tracker.input.ConsoleInput;
import ru.job4j.tracker.input.Input;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

/**
 * Пользовательский консольный интерфейс.
 * @author Alexey Makarov
 * @since 05.08.2018
 * @version 0.1
 */
public class StartUI {

    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";

    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String FIND_BY_ID = "4";

    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";

    /**
     * Получение данных от пользователя.
     */
    private Input input;

    /**
     * Хранилище заявок.
     */
    private Tracker tracker;

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
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (FIND_BY_ID.equals(answer)) {
                this.findById();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("\n------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки : ");
        String desc = this.input.ask("Введите описание заявки : ");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("\n------------ Новая заявка с getId : " + item.getId() + "-----------");
    }

    /**
     * Метод реализует поиск заявки по идентификатору.
     */
    private void findById() {
        System.out.println("\n------------ Поиск существующей заявки --------------");
        Long id = Long.parseLong(this.input.ask("Введите идентификатор(ID) заявки : "));
        Item item = tracker.findById(id);
        System.out.println("------------ Найдена заявка с getId : "
                + item.getId() + "-----------\n Имя: " + item.getName() + "\n Описание: "
                + item.getDescription());
    }

    /**
     * Метод показывает главное меню.
     */
    private void showMenu() {
        System.out.println("Меню.");
        System.out.println("0. Добавить новую заявку");
        System.out.println("1. Показать все заявки");
        System.out.println("2. Редактировать заявку");
        System.out.println("3. Удалить заявку");
        System.out.println("4. Найти заявку по идентификатору(id)");
        System.out.println("5. Найти заявку по имени");
        System.out.println("6. Выход из программы");
    }

    /**
     * Запуск программы.
     * @param args параметры.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
