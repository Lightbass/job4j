package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Телефонный справочни с поиском по ключу.
 * @author Alexey Makarov
 * @since 10.08.18
 * @version 0.1
 */
public class PhoneDictionary {
    private List<Person> persons = new ArrayList<Person>();

    /**
     * Добавить пользователя в справочник.
     * @param person добавляемый пользователь.
     */
    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        for (Person pers : persons) {
            if (pers.getName().contains(key)
                    || pers.getSurname().contains(key)
                    || pers.getPhone().contains(key)
                    || pers.getAddress().contains(key)
                    ) {
                result.add(pers);
            }
        }
        return result;
    }
}