package ru.job4j.user;

/**
 * Класс - пользователь, включающий в себя поля.
 * @author Alexey Makarov
 * @since 13.08.18
 * @version 0.1
 */
public class User {
    private int id;
    private String name;
    private String city;

    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * Возвращает идентификатор пользователя.
     * @return идентификатор.
     */
    public int getId() {
        return id;
    }

    /**
     * Возвращает имя пользователя.
     * @return имя.
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает город.
     * @return город.
     */
    public String getCity() {
        return city;
    }
}
