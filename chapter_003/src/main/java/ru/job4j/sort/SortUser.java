package ru.job4j.sort;

import java.util.*;

/**
 * Класс - сортирует список по возрасту(age).
 * @author Alexey Makarov
 * @since 13.08.18
 * @version 0.1
 */
public class SortUser {

    private Comparator<User> compLength = (o1, o2) -> {
        int n1 = o1.getName().length();
        int n2 = o2.getName().length();
        return n1 > n2 ? 1 : (n1 < n2 ? -1 : 0);
    };

    private Comparator<User> compNameOld = (o1, o2) -> {
        final int rs = o1.getName().compareTo(o2.getName());
        return rs;
    };

    /**
     * Возвращает отсортированный Set с пользователями из списка в параметрах.
     * @param list список пользователей.
     * @return отсортированное множество.
     */
    public Set<User> sort(List<User> list) {
        Set<User> result = new TreeSet<>();
        result.addAll(list);
        return result;
    }

    /**
     * Отсортировать список по длине имени.
     * @param list список.
     * @return отсортированный список.
     */
    public List<User> sortNameLength(List<User> list) {
        List<User> result = new ArrayList<>(list);
        result.sort(compLength);
        return result;
    }

    /**
     * Отсортировать список по обоим полям, сначала по имени, потом по возрасту.
     * @param list список.
     * @return отсортированный список.
     */
    public List<User> sortByAllFields(List<User> list) {
        List<User> result = new ArrayList<>(list);
        result.sort(compNameOld.thenComparing(user -> user.getAge()));
        return result;
    }
}
