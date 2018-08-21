package ru.job4j.map;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс - пользователь с информацией об имени, детях и дне рождения.
 * @author Alexey Makarov
 * @since 21.08.18
 * @version 0.1
 */
public class User {
    private String name;
    private int children;
    private Calendar birthday;

    /**
     * Метод инициализирует поля класса.
     * @param name имя.
     * @param children дети.
     * @param birthday день рождения.
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    /**
     * Метод заменяет объект имя.
     * @param name имя
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Метод заменяет объект дети.
     * @param children дети
     */
    public void setChildren(int children) {
        this.children = children;
    }

    /**
     * Метод заменяет объект день рождения.
     * @param birthday день рождения.
     */
    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    /**
     * Метод возвращает имя.
     * @return имя.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод возвращает объект дети.
     * @return имя.
     */
    public int getChildren() {
        return children;
    }

    /**
     * Метод возвращает день рождения.
     * @return имя.
     */
    public Calendar getBirthday() {
        return birthday;
    }
}

