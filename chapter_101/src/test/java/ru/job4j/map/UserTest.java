package ru.job4j.map;

import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Calendar;

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
}
