package ru.job4j.store;

/**
 * Класс - хранилище объектов ролей(Role).
 * @author Alexey Makarov
 * @since 17.08.18
 * @version 0.1
 */
public class RoleStore extends AbstractStore<Role> {

    /**
     * Конструктор инициализирует кол-во ролей в контейнере.
     * @param n количество ролей.
     */
    public RoleStore(int n) {
        super(n);
    }
}