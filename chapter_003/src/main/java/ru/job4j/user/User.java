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

    /**
     * Конструктор инициализирующий поля ID, имени и города.
     * @param id идентификатор.
     * @param name имя.
     * @param city город.
     */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (id != user.id) {
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        return city != null ? city.equals(user.city) : user.city == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id
                + ", name='" + name + '\''
                + ", city='" + city + '\'' + '}';
    }
}
