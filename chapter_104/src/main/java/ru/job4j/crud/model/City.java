package ru.job4j.crud.model;

/**
 * Класс - город.
 */
public class City {
    private int id;
    private String name;

    /**
     * Конструктор инициализирует поле имени.
     * @param name имя.
     */
    public City(String name) {
        this(-1, name);
    }

    /**
     * Конструктор инициализирует поле идентификатора и имени.
     * @param id идентификатор.
     * @param name имя.
     */
    public City(int id, String name) {
        this.name = name;
        this.id = id;
    }

    /**
     * Метод возвращает имя.
     * @return имя.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод изменяет имя внутри объекта.
     * @param name имя.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод возвращает идентификатор города.
     * @return идентификатор.
     */
    public int getId() {
        return id;
    }

    /**
     * Метод изменяет идентификатор города.
     * @param id идентификатор.
     */
    public void setId(int id) {
        this.id = id;
    }

}
