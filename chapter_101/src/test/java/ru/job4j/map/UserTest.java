package ru.job4j.map;

import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 * JUnit тест класса User.
 * @author Alexey Makarov
 * @since 20.08.18
 * @version 0.1
 */
public class UserTest {
    Calendar calendar;
    User user;

    @Before
    public void prepare() {
        calendar = Calendar.getInstance();
        user = new User("name", 1, calendar);
    }

    @Test
    public void whenCreateUserWithNameParentBirthdayThenOK() {
        assertThat(user.getName(), is("name"));
        assertThat(user.getChildren(), is(1));
        assertThat(user.getBirthday(), is(calendar));
    }

    @Test
    public void whenSetNameParentBirthdayThenOK() {
        user.setName("name2");
        user.setChildren(2);
        Calendar second = Calendar.getInstance();
        second.set(1, 1, 1);
        user.setBirthday(second);
        assertThat(user.getName(), is("name2"));
        assertThat(user.getChildren(), is(2));
        assertThat(user.getBirthday().equals(second), is(true));
    }

    /**
     * Тест для задания "2. Не перекрывать equals hashCode"
     */
    @Test
    public void whenTestMapWithTwoSameUsersThen2UsersInMap() {
        User user2 = new User("name", 2, calendar);
        Map<User, Object> map = new HashMap<>();
        map.put(user, new Object());
        map.put(user2, new Object());
        System.out.println(map);
        Hashtable<User, String> table = new Hashtable<>();
        table.put(user2, "string2");
        table.put(new User("name", 3, calendar), "string2");
        table.put(user, "string2");
        System.out.println(table);
        System.out.println(table.keySet());
    }
}
