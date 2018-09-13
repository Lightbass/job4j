package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса CustomLinkedList.
 * @author Alexey Makarov
 * @since 17.08.18
 * @version 0.1
 */
public class SimpleStackTest {
    SimpleStack<String> list;
    @Before
    public void prepare() {
        list = new SimpleStack<>();
        list.push("String 0");
        list.push("String 1");
        list.push("String 2");
        list.push("String 3");
        list.push("String 4");
    }

    @Test
    public void whenPullAllElementsThenOK() {
        assertThat(list.poll(),  is("String 4"));
        assertThat(list.poll(),  is("String 3"));
        assertThat(list.poll(),  is("String 2"));
        assertThat(list.poll(),  is("String 1"));
        assertThat(list.poll(),  is("String 0"));
    }
}
