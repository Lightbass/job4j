package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс - пользователь, включающий в себя поля.
 * @author Alexey Makarov
 * @since 13.08.18
 * @version 0.1
 */
public class User {
    /**
     * Имя.
     */
    private String name;

    /**
     * Паспорт = идентификатор.
     */
    private String passport;

    /**
     * Конструктор инициализирующий поля имени и возраста.
     * @param name имя.
     * @param passport паспорт.
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    /**
     * Метод возвращает имя.
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * Метод возврашает возраст.
     * @return возраст.
     */
    public String getPassport() {
        return passport;
    }

    /**
     * Сравнение.
     * @param o второй объект.
     * @return равны или нет.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return passport.equals(user.passport);
    }

    /**
     * Метод возвращает хеш-код.
     * @return хеш-код.
     */
    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}
