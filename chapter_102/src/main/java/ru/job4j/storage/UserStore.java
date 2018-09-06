package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс - хранилище пользователей.
 * @author Alexey Makarov
 * @since 05.09.18
 * @version 0.1
 */
@ThreadSafe
public class UserStore {

    @GuardedBy("this")
    private final List<User> list = new ArrayList<>();

    /**
     * Метод добавляет пользователя в хранилище.
     * @param user добавляемый пользователь.
     * @return true, если пользователь добавлен; false - если нет.
     */
    public synchronized boolean add(final User user) {
        boolean result = false;
        if (user != null) {
            if (findById(user.getId()) == -1) {
                result = list.add(user);
            }
        }
        return result;
    }

    /**
     * Метод возвращает пользователя из хранилища.
     * @param id идентификатор пользователя.
     * @return объект User; null, если пользователя нет в хранилище
     */
    public synchronized User get(int id) {
        User result = null;
        int index = findById(id);
        if (index != -1) {
                result = list.get(index);
        }
        return result;
    }

    /**
     * Метод обновляет пользователя внутри хранилища.
     * @param user обновляемый пользователь.
     * @return true, если пользователь обновлён; false - если нет.
     */
    public synchronized boolean update(final User user) {
        boolean result = false;
        if (user != null) {
            int index = findById(user.getId());
            if (index != -1) {
                list.set(index, user);
                result = true;
            }
        }
        return result;
    }

    /**
     * Метод удаляет пользователя из хранилища.
     * @param user удаляемый пользователь.
     * @return true, если пользователь удален; false - если нет.
     */
    public synchronized boolean delete(final User user) {
        boolean result = false;
        if (user != null) {
            int index = findById(user.getId());
            if (index != -1) {
                list.remove(index);
                result = true;
            }
        }
        return result;
    }

    /**
     * Перевод денежных средств от одного пользователя к другому с использованием идентификатора.
     * @param fromId откуда переводятся средства.
     * @param toId куда переводятся средтва.
     * @param amount кол-во переводимых денежных средств.
     * @return true, если перевод прошел успешно; false - если нет.
     */
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        if (fromId != toId) {
            int indexA = findById(fromId);
            int indexB = findById(toId);
            if (indexA != -1 && indexB != -1) {
                User userA = list.get(indexA);
                User userB = list.get(indexB);
                if (userA.getCash() >= amount) {
                    result = true;
                    userA.setCash(userA.getCash() - amount);
                    userB.setCash(userB.getCash() + amount);
                }
            }
        }
        return result;
    }

    /**
     * Метод ищет пользователя в списке по идентификатору.
     * @param id идентификатор.
     * @return индекс пользователя в списке с таким идентификатором.
     */
    private synchronized int findById(int id) {
        int result = -1;
        for (int i = 0; i != list.size(); i++) {
            if (list.get(i).getId() == id) {
                result = i;
                break;
            }
        }
        return result;
    }
}
