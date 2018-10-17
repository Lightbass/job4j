package ru.job4j.crud;

import java.util.Date;

/**
 * Класс - пользователь.
 */
public class User {
    static private int serial = 1;
    private int id;
    private String name;
    private String login;
    private String email;
    private Date createDate;

    /**
     * Конструктор инициализирует поля имени, логина и почты.
     * @param name имя.
     * @param login логин.
     * @param email почта.
     */
    public User(String name, String login, String email) {
        this(serial++, name, login, email, new Date().getTime());
    }

    /**
     * Конструктор инициализирует поля идентификатора, имени, логина и почты.
     * @param id идентификатор.
     * @param name имя.
     * @param login логин.
     * @param email почта.
     */
    public User(int id, String name, String login, String email, long createDate) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.id = id;
        this.createDate = new Date(createDate);
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
     * Метод возвращает логин пользователя.
     * @return логин
     */
    public String getLogin() {
        return login;
    }

    /**
     * Метод изменяет логин внутри объекта.
     * @param login логин.
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Метод возвращает текущий адрес почты.
     * @return адрес электронной почты.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Метод изменяет текущий адрес электронной почты.
     * @param email адрес почты.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Метод возвращает идентификатор пользователя.
     * @return идентификатор.
     */
    public int getId() {
        return id;
    }

    /**
     * Метод изменяет идентификатор пользователя.
     * @param id идентификатор.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Метод возвращает дату создания пользователя.
     * @return дата создания.
     */
    public Date getCreateDate() {
        return createDate;
    }
}
