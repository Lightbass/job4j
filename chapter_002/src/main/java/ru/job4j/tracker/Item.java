package ru.job4j.tracker;

/**
 * Created by a.makarov on 03.08.2018.
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
}
