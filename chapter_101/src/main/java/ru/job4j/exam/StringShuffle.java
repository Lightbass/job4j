package ru.job4j.exam;

import java.util.*;

/**
 * Класс для определения слов анаграмм.
 * @author Alexey Makarov
 * @since 30.08.18
 * @version 0.1
 */
public class StringShuffle {

    /**
     * Метод возвращает коллекцию с анаграммами внутри ключей.
     * @param str массив строк, которые могут быть анаграммами.
     * @return отображение с упорядоченными сочетаниями символов.
     */
    public Map<String, String> isShuffled(String[] str) {
        Map<String, String> map = new HashMap<>();
        for (String current : str) {
            char[] chars = current.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            String value = map.get(key);
            if (value == null) {
                map.put(key, current);
            } else {
                map.put(key, value + " " + current);
            }
        }
        return map;
    }

    /**
     * Метод определяет, являются ли строки анаграммами друг друга.
     * @param first первая строка.
     * @param second вторая строка.
     * @return true, если слова являются анаграммами, false - если нет.
     */
    public boolean isShuffled2(String first, String second) {
        boolean result = false;
        if (first.length() == second.length()) {
            List<Character> list = new ArrayList<>();
            for (Character character : first.toCharArray()) {
                list.add(character);
            }
            for (Character character : second.toCharArray()) {
                list.remove(character);
            }
            result = list.isEmpty();
        }
        return result;
    }

    /**
     * Метод определяет, являются ли строки анаграммами друг друга.
     * @param first первая строка.
     * @param second вторая строка.
     * @return true, если слова являются анаграммами, false - если нет.
     */
    public boolean isShuffled3(String first, String second) {
        char[] chars1 = first.toCharArray();
        char[] chars2 = second.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        return Arrays.equals(chars1, chars2);
    }

    /**
     * Метод определяет, являются ли строки анаграммами друг друга.
     * @param first первая строка.
     * @param second вторая строка.
     * @return true, если слова являются анаграммами, false - если нет.
     */
    public boolean isShuffled4(String first, String second) {
        Set<String> set = new HashSet<>();
        char[] chars1 = first.toCharArray();
        char[] chars2 = second.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        set.add(new String(chars1));
        set.add(new String(chars2));
        return set.size() == 1;
    }

    /**
     * Метод определяет, являются ли строки анаграммами друг друга.
     * @param first первая строка.
     * @param second вторая строка.
     * @return true, если слова являются анаграммами, false - если нет.
     */
    public boolean containsAll(String first, String second) {
        boolean result = false;
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        if (first.length() == second.length()) {
            for (int i = 0; i != first.length(); i++) {
                Integer temp1 = map1.get(first.charAt(i));
                Integer temp2 = map2.get(second.charAt(i));
                map1.put(first.charAt(i), temp1 == null ? 1 : (temp1 + 1));
                map2.put(second.charAt(i), temp2 == null ? 1 : (temp2 + 1));
            }
            for (Character ch : first.toCharArray()) {
                if (!map1.get(ch).equals(map2.get(ch))) {
                    break;
                }
            }
            result = true;
        }
        return result;
    }
}
