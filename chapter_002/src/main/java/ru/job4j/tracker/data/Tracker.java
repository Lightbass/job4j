package ru.job4j.tracker.data;

import java.util.Arrays;
import java.util.Random;

/**
 * Класс - программа учета заявок.
 * @author Alexey Makarov
 * @since 03.08.2018
 * @version 0.1
 */
public class Tracker {

    /**
     * Максимальное кол-во заявок в трекере.
     */
    private int length = 100;

    /**
     * Массив для хранения заявок.
     */
    private Item[] items = new Item[length];
    private Random r = new Random();

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод реализаущий добавление заявки в хранилище.
     * @param item новая заявка.
     * @param id уникальный идентификатор.
     * @return возвращает добавленную заявку.
     */
    private Item add(Item item, long id) {
        item.setId(id);
        this.items[this.position++] = item;
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
        for (int i = 0; i != position; i++) {
            if (items[i].getId() == id) {
                --position;
                System.arraycopy(items, i + 1, items, i, position - i);
                items[position] = null;
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
    public Item[] findAll() {
        Item[] result = new Item[position];
        System.arraycopy(items, 0, result, 0, position);
        return result;
    }

    /**
     * Найти заявки с указанным именем и вывод их в качестве массива.
     * @param key искомое имя в заявках.
     * @return массив заявок с данным именем.
     */
    public Item[] findByName(String key) {
        int count = 0;
        Item[] result = new Item[length];
        for (int i = 0; i != position; i++) {
            if (items[i].getName().equals(key)) {
               result[count++] = items[i];
            }
        }
        return Arrays.copyOf(result, count);
    }

    /**
     * Найти заявку с указанным идентификатором.
     * @param id идентификатор искомой заявки.
     * @return заявка.
     */
    public Item findById(long id) {
        Item item = null;
        for (int i = 0; i != position; i++) {
            if (items[i].getId() == id) {
                item = items[i];
                break;
            }
        }
        return item;
    }
}
