package ru.job4j.tracker.data;

import java.util.Objects;

/**
 * Класс - заявка.
 * @author Alexey Makarov
 * @since 03.08.2018
 * @version 0.1
 */
public class Item {

    /**
     * Имя заявки.
     */
    private String name;

    /**
     * Идентификатор заявки.
     */
    private long id;

    /**
     * Описание заявки.
     */
    private String description;

    /**
     * Создание заявки с указанием характеристик в параметрах.
     * @param name имя.
     * @param desc описание.
     * @param id идентификатор.
     */
    public Item(String name, String desc, long id) {
        this.name = name;
        this.id = id;
        this.description = desc;
    }

    /**
     * Создание заявки без идентификатора.
     * @param name имя.
     * @param desc описание.
     */
    public Item(String name, String desc) {
        this(name, desc, 0);
    }

    /**
     * Изменить имя на другое.
     * @param name новое имя.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Изменить идентификатор на другой.
     * @param id новый идентификатор.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Изменить описание на другое.
     * @param desc новое описание.
     */
    public void setDescription(String desc) {
        this.description = desc;
    }

    /**
     * Получить имя
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * Получить идентификатор.
     * @return идентификатор.
     */
    public long getId() {
        return id;
    }

    /**
     * Получить описание.
     * @return описание.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Вывод заявки
     */
    @Override
    public String toString() {
        return new StringBuilder().append("Заявка: ")
                .append(name)
                .append(System.lineSeparator())
                .append("Описание: ")
                .append(description)
                .append(System.lineSeparator())
                .append("ID: ")
                .append(id).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(name, item.name)
                && Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description);
    }
}
