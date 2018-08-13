package ru.job4j.user;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * JUnit тест класса UserConvert.
 * @author Alexey Makarov
 * @since 10.08.18
 * @version 0.1
 */
public class UserConvertTest {

    /**
     * Проверка метода process().
     */
    @Test
    public void whenListToMapThenOK() {
        UserConvert uconv = new UserConvert();
        List<User> list = new ArrayList<>();
        User u1 = new User(5, "Alexey", "Spb");
        User u2 = new User(2, "Maksim", "Moscow");
        User u3 = new User(7, "Ivan", "Vladivostok");
        list.add(u1);
        list.add(u2);
        list.add(u3);
        HashMap<Integer, User> answer = new HashMap<>();
        answer.put(5, u1);
        answer.put(2, u2);
        answer.put(7, u3);
        assertThat(uconv.process(list), is(answer));
    }
}
