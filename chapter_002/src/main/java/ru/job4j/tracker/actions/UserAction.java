package ru.job4j.tracker.actions;

import ru.job4j.tracker.data.Item;
import ru.job4j.tracker.data.ItemContainer;
import ru.job4j.tracker.input.Input;

/**
 * Интерфейс - осуществляемое пользователем действие.
 * @author Alexey Makarov
 * @since 07.08.2018
 * @version 0.1
 */
public interface UserAction {

    /**
     * Переход на следующую строку.
     */
    String LS = System.lineSeparator();

    /**
     * Валидатор, проверяющий идентификатор на валидность.
     * @param str строка с идентификатором.
     * @param tracker трекер заявок.
     * @return
     */
    default Item checkStrGetItem(String str, ItemContainer tracker) {
        Item result = null;
        try {
            long id = Long.parseLong(str);
            result = tracker.findById(id);
            if (result == null) {
                System.out.println(LS + "------------ Заявок с таким идентификатором не существует --------------");
            }
        } catch (NumberFormatException nfe) {
            System.out.println(LS + "------------ Введено недопустимое значение -----------");
        }
        return result;
    }

    /**
     * Метод возвращает ключ опции.
     * @return ключ
     */
    int key();

    /**
     * Основной метод.
     * @param input объект типа Input
     * @param tracker объект типа Tracker
     */
    void execute(Input input, ItemContainer tracker);

    /**
     * Метод возвращает информацию о данном пункте меню.
     * @return Строка меню
     */
    String info();
}
