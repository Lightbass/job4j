package ru.job4j.tracker;

import ru.job4j.tracker.actions.*;
import ru.job4j.tracker.data.*;
import ru.job4j.tracker.input.*;
import java.util.*;

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
    class EditItem implements UserAction {

        private final int key;
        private final String info;

        /**
         * Конструктор инициализирует информацию о данном действии и его ключ.
         * @param key ключ
         * @param info информация.
         */
        public EditItem(int key, String info) {
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

        /**
         * Информация об операции AddAction.
         * @return строка с информацией.
         */
        @Override
        public String info() {
            return info;
        }
    }

    /**
     * Класс для действия - удаление заявки.
     * @author Alexey Makarov
     * @since 07.08.2018
     * @version 0.1
     */
    class DeleteItem implements UserAction {

        private final int key;
        private final String info;

        /**
         * Конструктор инициализирует информацию о данном действии и его ключ.
         * @param key ключ
         * @param info информация.
         */
        public DeleteItem(int key, String info) {
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
            System.out.println(LS + "------------ Удаление заявки --------------" + LS);
            Item item = checkStrGetItem(input.ask("Введите ID заявки: "), tracker);
            if (item != null) {
                tracker.delete(item.getId());
                System.out.println(LS + "------------ Удаление завершено --------------");
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

    /**
     * @param хранит ссылку на объект .
     */
    private Input input;
    /**
     * @param хранит ссылку на объект .
     */
    private Tracker tracker;
    /**
     * @param хранит ссылку на массив типа UserAction.
     */
    private List<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор.
     *
     * @param input   объект типа Input
     * @param tracker объект типа Tracker
     */
    public MenuTracker(Input input, Tracker tracker) {
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
        this.actions.add(new AddItem(0, "0. Добавить новую заявку"));
        this.actions.add(new ShowItems(1, "1. Показать все заявки"));
        this.actions.add(new MenuTracker.EditItem(2, "2. Редактировать заявку"));
        this.actions.add(new MenuTracker.DeleteItem(3, "3. Удалить заявку"));
        this.actions.add(new FindItemById(4, "4. Найти заявку по идентификатору(id)"));
        this.actions.add(new FindItemsByName(5, "5. Найти заявку по имени"));
        this.actions.add(new ExitProgram(6, "6. Выход из программы"));
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
}
