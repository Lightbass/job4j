package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

/**
 * Класс - пользователь.
 * @author Alexey Makarov
 * @since 05.09.18
 * @version 0.1
 */
public class User {
    private int id;
    private int cash;

    /**
     * Конструктор инициализирует поля имени и кол-ва денег.
     * @param id идентификатор.
     * @param cash кол-во денежных средств.
     */
    public User(int id, int cash) {
        this.id = id;
        this.cash = cash;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getId() {
        return id;
    }
}
