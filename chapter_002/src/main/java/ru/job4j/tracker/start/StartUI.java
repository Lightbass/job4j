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
     * Константа меню для отображения всех заявок.
     */
    private static final String SHOW_ALL = "1";

    /**
     * Константа меню для редактирования заявки.
     */
    private static final String EDIT = "2";

    /**
     * Константа меню для удаления заявки.
     */
    private static final String DELETE = "3";

    /**
     * Константа меню для поиска заявки по идентификатору.
     */
    private static final String FIND_BY_ID = "4";

    /**
     * Константа меню для поиска заявок по имени.
     */
    private static final String FIND_BY_NAME = "5";

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
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW_ALL.equals(answer)) {
                this.showAllItems();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DELETE.equals(answer)) {
                this.deleteItem();
            } else if (FIND_BY_ID.equals(answer)) {
                this.findById();
            } else if (FIND_BY_NAME.equals(answer)) {
                this.findByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println(nextLine + "------------ Добавление новой заявки --------------" + nextLine);
        String name = this.input.ask("Введите имя заявки : ");
        String desc = this.input.ask("Введите описание заявки : ");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println(nextLine + item + nextLine + nextLine + "------------ Новая заявка создана -----------" + nextLine);
    }

    /**
     * Метод реализует вывод всех заявок.
     */
    private void showAllItems() {
        System.out.println(nextLine + "------------ Заявки в трекере --------------" + nextLine);
        Item[] items = tracker.findAll();
        for (Item i : items) {
            System.out.println(i + nextLine);
        }
        System.out.println("------------ Перечисление завершено --------------");
    }

    /**
     * Метод реализует редактирование заявки.
     */
    private void editItem() {
        System.out.println(nextLine + "------------ Редактирование заявки --------------" + nextLine);
        Item item = checkStrGetItem(input.ask("Введите ID заявки: "));
        if (item != null) {
                System.out.println(nextLine + item);
                item.setName(input.ask("Введите новое имя заявки: "));
                item.setDescription(input.ask("Введите новое описание заявки: "));
                tracker.replace(item.getId(), item);
                System.out.println(nextLine + "------------ Редактирование завершено --------------");
        }
    }

    /**
     * Метод реализует удаление заявки.
     */
    private void deleteItem() {
        System.out.println(nextLine + "------------ Удаление заявки --------------" + nextLine);
        Item item = checkStrGetItem(input.ask("Введите ID заявки: "));
        if (item != null) {
            tracker.delete(item.getId());
            System.out.println(nextLine + "------------ Удаление завершено --------------");
        }
    }

    /**
     * Метод реализует поиск заявок по имени.
     */
    private void findByName() {
        System.out.println(nextLine + "------------ Поиск заявок по имени --------------" + nextLine);
        String findName = input.ask("Введите искомое имя заявки: ");
        Item[] items = tracker.findByName(findName);
        if (items.length == 0) {
            System.out.println(nextLine + "------------ Заявок с таким именем не существует --------------");
            return;
        }
        for (Item i : items) {
            System.out.println(nextLine + i);
        }
        System.out.println(nextLine + "------------ Перечисление завершено --------------");
    }

    /**
     * Метод реализует поиск заявки по идентификатору.
     */
    private void findById() {
        System.out.println(nextLine + "------------ Поиск заявки по идентификатору --------------" + nextLine);
        Item item = checkStrGetItem(this.input.ask("Введите идентификатор(ID) заявки : "));
        if (item != null) {
            System.out.println(nextLine + item + nextLine + nextLine + "------------ Найдена заявка -----------");
        }
    }

    /**
     * Метод показывает главное меню.
     */
    private void showMenu() {
        System.out.println(nextLine + "Меню.");
        System.out.println("0. Добавить новую заявку");
        System.out.println("1. Показать все заявки");
        System.out.println("2. Редактировать заявку");
        System.out.println("3. Удалить заявку");
        System.out.println("4. Найти заявку по идентификатору(id)");
        System.out.println("5. Найти заявку по имени");
        System.out.println("6. Выход из программы");
    }

    private Item checkStrGetItem(String str) {
        Item result = null;
        try {
            long id = Long.parseLong(str);
            result = tracker.findById(id);
            if (result == null) {
                System.out.println(nextLine + "------------ Заявок с таким идентификатором не существует --------------");
            }
        } catch (NumberFormatException nfe) {
            System.out.println(nextLine + "------------ Введено недопустимое значение -----------");
        }
        return result;
    }

    /**
     * Запуск программы.
     * @param args параметры.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}
