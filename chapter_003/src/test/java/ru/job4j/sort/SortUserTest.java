package ru.job4j.sort;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса SortUser.
 * @author Alexey Makarov
 * @since 13.08.18
 * @version 0.1
 */
public class SortUserTest {

    /**
     * Проверка метода sort().
     */
    @Test
    public void whenListToMapThenOK() {
        SortUser su = new SortUser();
        List<User> list = new ArrayList<>();
        User u1 = new User("Alexey", 20);
        User u2 = new User("Mikhail", 30);
        User u3 = new User("Maria", 15);
        User u4 = new User("Ivan", 18);
        list.add(u1);
        list.add(u2);
        list.add(u3);
        list.add(u4);
        Set<User> result = su.sort(list);
        Iterator<User> it = result.iterator();
        assertThat(it.next(), is(u3));
        assertThat(it.next(), is(u4));
        assertThat(it.next(), is(u1));
        assertThat(it.next(), is(u2));
    }
}
