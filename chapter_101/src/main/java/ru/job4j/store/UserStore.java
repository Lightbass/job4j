package ru.job4j.store;

import ru.job4j.generic.SimpleArray;

/**
 * Класс - хранилище объектов пользователей(User).
 * @author Alexey Makarov
 * @since 17.08.18
 * @version 0.1
 */
public class UserStore extends AbstractStore<User> {

    /**
     * Конструктор инициализирует кол-во пользователей в контейнере.
     * @param n количество пользователей.
     */
    public UserStore(int n) {
        super(n);
    }
}
