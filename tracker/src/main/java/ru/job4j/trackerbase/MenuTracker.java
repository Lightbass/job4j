package ru.job4j.trackerbase;

import ru.job4j.trackerbase.actions.BaseAction;
import ru.job4j.trackerbase.actions.UserAction;
import ru.job4j.trackerbase.data.Item;
import ru.job4j.trackerbase.data.ItemContainer;
import ru.job4j.trackerbase.input.Input;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для меню.
 * @author Alexey Makarov
 * @since 07.08.2018
 * @version 0.1
 */
public class MenuTracker {

    /**
     * Класс для действия - редактирование заявки.
     * @author Alexey Makarov
     * @since 07.08.2018
     * @version 0.1
     */
    private class EditItem extends BaseAction {

        /**
         * Конструктор инициализирует информацию о данном действии и его ключ.
         * @param key  ключ
         * @param info информация.
         */
        public EditItem(int key, String info) {
            super(key, info);
        }

        /**
         * Основной метод.
         * @param input объект типа Input
         * @param tracker объект типа Tracker
         */
        @Override
        public void execute(Input input, ItemContainer tracker) {
            System.out.println(LS + "------------ Редактирование заявки --------------" + LS);
            Item item = checkStrGetItem(input.ask("Введите ID заявки: "), tracker);
            if (item != null) {
                System.out.println(LS + item);
                item.setName(input.ask("Введите новое имя заявки: "));
                item.setDescription(input.ask("Введите новое описание заявки: "));
                tracker.replace(item.getId(), item);
                System.out.println(LS + "------------ Редактирование завершено --------------");
            }
        }
    }

    /**
     * Класс для действия - удаление заявки.
     * @author Alexey Makarov
     * @since 07.08.2018
     * @version 0.1
     */
    private class DeleteItem extends BaseAction {

        /**
         * Конструктор инициализирует информацию о данном действии и его ключ.
         * @param key  ключ
         * @param info информация.
         */
        public DeleteItem(int key, String info) {
            super(key, info);
        }

        /**
         * Основной метод.
         * @param input объект типа Input
         * @param tracker объект типа Tracker
         */
        @Override
        public void execute(Input input, ItemContainer tracker) {
            System.out.println(LS + "------------ Удаление заявки --------------" + LS);
            Item item = checkStrGetItem(input.ask("Введите ID заявки: "), tracker);
            if (item != null) {
                tracker.delete(item.getId());
                System.out.println(LS + "------------ Удаление завершено --------------");
            }
        }
    }

    /**
     * Класс для действия - отображение всех заявок.
     * @author Alexey Makarov
     * @since 07.08.2018
     * @version 0.1
     */
    private class ShowItems extends BaseAction {

        /**
         * Конструктор инициализирует информацию о данном действии и его ключ.
         * @param key  ключ
         * @param info информация.
         */
        public ShowItems(int key, String info) {
            super(key, info);
        }

        /**
         * Основной метод.
         * @param input объект типа Input
         * @param tracker объект типа Tracker
         */
        @Override
        public void execute(Input input, ItemContainer tracker) {
            System.out.println(LS + "------------ Заявки в трекере --------------" + LS);
            List<Item> items = tracker.findAll();
            for (Item i : items) {
                System.out.println(i + LS);
            }
            System.out.println("------------ Перечисление завершено --------------");
        }
    }

    /**
     * Класс для действия - поиск заявки по имени.
     * @author Alexey Makarov
     * @since 07.08.2018
     * @version 0.1
     */
    private class FindItemsByName extends BaseAction {

        /**
         * Конструктор инициализирует информацию о данном действии и его ключ.
         * @param key  ключ
         * @param info информация.
         */
        public FindItemsByName(int key, String info) {
            super(key, info);
        }

        /**
         * Основной метод.
         * @param input объект типа Input
         * @param tracker объект типа Tracker
         */
        @Override
        public void execute(Input input, ItemContainer tracker) {
            System.out.println(LS + "------------ Поиск заявок по имени --------------" + LS);
            String findName = input.ask("Введите искомое имя заявки: ");
            List<Item> items = tracker.findByName(findName);
            if (items.isEmpty()) {
                System.out.println(LS + "------------ Заявок с таким именем не существует --------------");
                return;
            }
            for (Item i : items) {
                System.out.println(LS + i);
            }
            System.out.println(LS + "------------ Перечисление завершено --------------");
        }
    }

    /**
     * Класс для действия - поиск заявки по идентификатору.
     * @author Alexey Makarov
     * @since 07.08.2018
     * @version 0.1
     */
    private class FindItemById extends BaseAction {

        /**
         * Конструктор инициализирует информацию о данном действии и его ключ.
         * @param key  ключ
         * @param info информация.
         */
        public FindItemById(int key, String info) {
            super(key, info);
        }

        /**
         * Основной метод.
         * @param input объект типа Input
         * @param tracker объект типа Tracker
         */
        @Override
        public void execute(Input input, ItemContainer tracker) {
            System.out.println(LS + "------------ Поиск заявки по идентификатору --------------" + LS);
            Item item = checkStrGetItem(input.ask("Введите идентификатор(ID) заявки : "), tracker);
            if (item != null) {
                System.out.println(LS + item + LS + LS + "------------ Найдена заявка -----------");
            }
        }
    }

    /**
     * Класс для действия - выход из программы.
     * @author Alexey Makarov
     * @since 07.08.2018
     * @version 0.1
     */
    private class ExitProgram extends BaseAction {

        /**
         * Конструктор инициализирует информацию о данном действии и его ключ.
         * @param key  ключ
         * @param info информация.
         */
        public ExitProgram(int key, String info) {
            super(key, info);
        }

        /**
         * Основной метод.
         * @param input объект типа Input
         * @param tracker объект типа Tracker
         */
        @Override
        public void execute(Input input, ItemContainer tracker) {
            System.out.println(LS + "------------ Выход из программы --------------" + LS);
            exit = true;
        }
    }

    /**
     * Класс для действия - добавление новой заявки.
     * @author Alexey Makarov
     * @since 07.08.2018
     * @version 0.1
     */
    private class AddItem extends BaseAction {

        /**
         * Конструктор инициализирует информацию о данном действии и его ключ.
         * @param key  ключ
         * @param info информация.
         */
        public AddItem(int key, String info) {
            super(key, info);
        }

        /**
         * Основной метод.
         * @param input объект типа Input
         * @param tracker объект типа Tracker
         */
        @Override
        public void execute(Input input, ItemContainer tracker) {
            System.out.println(LS + "------------ Добавление новой заявки --------------" + LS);
            String name = input.ask("Введите имя заявки : ");
            String desc = input.ask("Введите описание заявки : ");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println(LS + item + LS + LS + "------------ Новая заявка создана -----------");
        }
    }

    /**
     * @param хранит ссылку на объект .
     */
    private Input input;

    /**
     * @param хранит ссылку на объект .
     */
    private ItemContainer tracker;

    /**
     * @param хранит ссылку на массив типа UserAction.
     */
    private List<UserAction> actions = new ArrayList<>();

    /**
     * Хранит активное состояние меню.
     */
    private boolean exit = false;

    /**
     * Конструктор.
     *
     * @param input   объект типа Input
     * @param tracker объект типа Tracker
     */
    public MenuTracker(Input input, ItemContainer tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод для получения массива меню.
     *
     * @return длину массива
     */
    public int getActionsLentgh() {
        return this.actions.size();
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions() {
        this.actions.add(new AddItem(0, "Добавить новую заявку"));
        this.actions.add(new ShowItems(1, "Показать все заявки"));
        this.actions.add(new EditItem(2, "Редактировать заявку"));
        this.actions.add(new DeleteItem(3, "Удалить заявку"));
        this.actions.add(new FindItemById(4, "Найти заявку по идентификатору(id)"));
        this.actions.add(new FindItemsByName(5, "Найти заявку по имени"));
        this.actions.add(new ExitProgram(6, "Выход из программы"));
    }

    /**
     * Метод возвращает массив ключей действий.
     * @return массив ключей.
     */
    public int[] getActionKeys() {
        int[] result = new int[actions.size()];
        for (int i = 0; i != actions.size(); i++) {
            result[i] = actions.get(i).key();
        }
        return result;
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        System.out.println(System.lineSeparator() + "Меню.");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    public boolean isExit() {
        return exit;
    }
}
