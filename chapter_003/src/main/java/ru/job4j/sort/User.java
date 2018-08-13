package ru.job4j.sort;

/**
 * Класс - пользователь, включающий в себя поля.
 * @author Alexey Makarov
 * @since 13.08.18
 * @version 0.1
 */
public class User implements Comparable<User> {
    private String name;
    private int age;

    /**
     * Конструктор инициализирующий поля имени и возраста.
     * @param name имя.
     * @param age возраст.
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /**
     * Сравнение двух пользователей по возрасту.
     * @param o пользователь с которым происходит сравнение.
     * @return результат сравнения.
     */
    @Override
    public int compareTo(User o) {
        return age > o.age ? 1 : (age < o.age ? -1 : 0);
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
    public int getAge() {
        return age;
    }
}
