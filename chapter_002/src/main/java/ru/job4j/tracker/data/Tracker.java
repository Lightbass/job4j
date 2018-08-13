package ru.job4j.tracker.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Класс - программа учета заявок.
 * @author Alexey Makarov
 * @since 03.08.2018
 * @version 0.1
 */
public class Tracker {

    /**
     * Массив для хранения заявок.
     */
    private ArrayList<Item> items = new ArrayList<>();
    private Random r = new Random();

    /**
     * Метод реализаущий добавление заявки в хранилище.
     * @param item новая заявка.
     * @param id уникальный идентификатор.
     * @return возвращает добавленную заявку.
     */
    private Item add(Item item, long id) {
        item.setId(id);
        this.items.add(item);
        return item;
    }

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     * @return возвращает добавленную заявку.
     */
    public Item add(Item item) {
        return add(item, this.generateId());
    }

    /**
     * Генерация уникального идентификатора.
     * @return идентификатор.
     */
    private long generateId() {
        long result = r.nextLong();
        while (findById(result) != null) {
            result = r.nextLong();
        }
        return result;
    }

    /**
     * Замена/редактирование заявки с указанным айди, на другую.
     * @param id идентификатор заявки, которую нужно заменить/редактировать.
     * @param item заявка на которую нужно заменить.
     */
    public boolean replace(long id, Item item) {
        boolean result = false;
        if (delete(id)) {
            item.setId(id);
            add(item, id);
            result = true;
        }
        return result;
    }

    /**
     * Удаление заявки из трекера.
     * @param id идентификатор заявки.
     */
    public boolean delete(long id) {
        boolean result = false;
        for (int i = 0; i != items.size(); i++) {
            if (items.get(i).getId() == id) {
                items.remove(items.get(i));
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Вывод массива со всеми заявками.
     * @return массив с заявками.
     */
    public List<Item> findAll() {
        return items;
    }

    /**
     * Найти заявки с указанным именем и вывод их в качестве массива.
     * @param key искомое имя в заявках.
     * @return массив заявок с данным именем.
     */
    public List<Item> findByName(String key) {
        List<Item> result = new ArrayList<>();
        for (int i = 0; i != items.size(); i++) {
            if (items.get(i).getName().equals(key)) {
               result.add(items.get(i));
            }
        }
        return result;
    }

    /**
     * Найти заявку с указанным идентификатором.
     * @param id идентификатор искомой заявки.
     * @return заявка.
     */
    public Item findById(long id) {
        Item item = null;
        for (int i = 0; i != items.size(); i++) {
            if (items.get(i).getId() == id) {
                item = items.get(i);
                break;
            }
        }
        return item;
    }
}
