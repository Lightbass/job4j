package ru.job4j.user;

import java.util.HashMap;
import java.util.List;

/**
 * Класс - конвертор для класса User.
 * @author Alexey Makarov
 * @since 13.08.18
 * @version 0.1
 */
public class UserConvert {

    /**
     * Возвращает Map с ключами Id и значениями User из списка, указанного в параметрах.
     * @param list список с пользователями.
     * @return Map с теми же пользователями под ключами id.
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> hm = new HashMap<>();
        for (User user : list) {
            hm.put(user.getId(), user);
        }
        return hm;
    }
}
