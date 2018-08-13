package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Класс - сортирует список по возрасту(age).
 * @author Alexey Makarov
 * @since 13.08.18
 * @version 0.1
 */
public class SortUser {

    /**
     * Возвращает отсортированный Set с пользователями из списка в параметрах.
     * @param list список пользователей.
     * @return отсортированное множество.
     */
    public Set<User> sort(List<User> list) {
        Set<User> result = new TreeSet<>();
        for (User user : list) {
            result.add(user);
        }
        return result;
    }
}
