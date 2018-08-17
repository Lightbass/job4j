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
public class SimpleQueueTest {
    SimpleQueue<String> list;
    @Before
    public void prepare() {
        list = new SimpleQueue<>();
        list.push("String 0");
        list.push("String 1");
        list.push("String 2");
        list.push("String 3");
        list.push("String 4");
    }

    @Test
    public void whenPullAllElementsThenOK() {
        assertThat(list.poll().equals("String 0"),  is(true));
        assertThat(list.poll().equals("String 1"),  is(true));
        assertThat(list.poll().equals("String 2"),  is(true));
        assertThat(list.poll().equals("String 3"),  is(true));
        assertThat(list.poll().equals("String 4"),  is(true));
    }
}
