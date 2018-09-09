package ru.job4j.cache;

/**
 * Класс - модель.
 * @author Alexey Makarov
 * @since 08.09.18
 * @version 0.1
 */
public class Base implements Cloneable {
    private int id;
    private int version;
    private String name;

    /**
     * Конструктор инициализирует поля модели.
     * @param id идентификатор.
     * @param name имя.
     */
    public Base(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Метод возвращает идентификатор модели.
     * @return идентификатор.
     */
    public int getId() {
        return id;
    }

    /**
     * Метод возвращает версию модели.
     * @return версия.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Метод возвращает имя модели.
     * @return имя.
     */
    public String getName() {
        return name;
    }

    /**
     * Метод изменяет имя модели.
     * @param name версия.
     */
    public void setName(String name) {
        this.version++;
        this.name = name;
    }

    /**
     * Клонирует модель.
     * @return копия модели.
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
