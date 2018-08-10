package ru.job4j.search;

/**
 * Задача с заданным приоритетом и описанием.
 * @author Alexey Makarov
 * @since 10.08.18
 * @version 0.1
 */
public class Task {
    private String desc;
    private int priority;

    /**
     * Конструктор инициализирует описание и приоритет.
     * @param desc описание.
     * @param priority приоритет.
     */
    public Task(String desc, int priority) {
        this.desc = desc;
        this.priority = priority;
    }

    /**
     * Метод возвращает описание.
     * @return описание.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Метод возвращает приоритет.
     * @return приоритет.
     */
    public int getPriority() {
        return priority;
    }
}