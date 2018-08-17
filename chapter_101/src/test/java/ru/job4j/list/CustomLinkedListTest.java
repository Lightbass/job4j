package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса CustomLinkedList.
 * @author Alexey Makarov
 * @since 17.08.18
 * @version 0.1
 */
public class CustomLinkedListTest {
    CustomLinkedList<String> list;
    @Before
    public void prepare() {
        list = new CustomLinkedList<>();
        list.add("String 0");
        list.add("String 1");
        list.add("String 2");
        list.add("String 3");
        list.add("String 4");
    }

    @Test
    public void thenDeleteElementsByIndexThenResultOK() {
        list.delete(0);
        list.delete(2);
        assertThat(list.get(0).equals("String 1"),  is(true));
        assertThat(list.get(1).equals("String 2"),  is(true));
        assertThat(list.get(2).equals("String 4"),  is(true));
    }

    @Test
    public void thenGetElementsInIndexThenResultOK() {
        for (int i = 0; i != 5; i++) {
            assertThat(list.get(i).equals("String " + i), is(true));
        }
    }

    @Test
    public void thenAddsMoreThanTenElementsInListThenResultOK() {
        list.add("String 5");
        list.add("String 6");
        list.add("String 7");
        list.add("String 8");
        list.add("String 9");
        list.add("String 10");
        list.add("String 11");
        Iterator<String> iter = list.iterator();
        for (int i = 0; i != 12; i++) {
            assertThat(iter.next().equals("String " + i), is(true));
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModListOnWorkingIteratorThenException() {
        Iterator<String> iter = list.iterator();
        iter.next();
        iter.next();
        list.add("NOOOOOOOOOOOOOOOOOOOOO");
        iter.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorEndsThenException() {
        Iterator<String> iter = list.iterator();
        iter.next();
        iter.next();
        iter.next();
        iter.next();
        iter.next();
        iter.next();
    }
}
