package ru.job4j.sort;

import java.util.*;

/**
 * Класс - сортирует список по подразделениям.
 * @author Alexey Makarov
 * @since 15.08.18
 * @version 0.1
 */
public class SortGroup {

    private String[] addBaseGroup(String[] str) {
        Set<String> set = new HashSet(Arrays.asList(str));
        for (String group : str) {
            if (group.contains("\\")) {
                String mainGroup = group.substring(0, group.indexOf("\\"));
                if (!set.contains(mainGroup)) {
                    set.add(mainGroup);
                }
            }
        }
        str = set.toArray(new String[set.size()]);
        return str;
    }

    /**
     * Сортировка по возрастанию.
     * @param str массив с подразделениями.
     */
    public String[] ascSort(String[] str) {
        str = addBaseGroup(str);
        Arrays.sort(str);
        return str;
    }

    /**
     * Сортировка по убыванию.
     * @param str массив с подразделениями.
     */
    public String[] descSort(String[] str) {
        str = addBaseGroup(str);
        Arrays.sort(str, (s1, s2) -> {
            int result = s1.length() - s2.length();
            int min = Math.min(s1.length(), s2.length());
            for (int i = 0; i != min; i++) {
                if (s1.charAt(i) - s2.charAt(i) != 0) {
                    result = s2.charAt(i) - s1.charAt(i);
                    break;
                }
            }
            return result;
        });
        return str;
    }


}
