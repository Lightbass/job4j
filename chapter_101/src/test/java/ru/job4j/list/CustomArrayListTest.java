package ru.job4j.list;

import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса SimpleArrayList.
 * @author Alexey Makarov
 * @since 17.08.18
 * @version 0.1
 */
public class CustomArrayListTest {
    CustomArrayList<String> list;
    @Before
    public void prepare() {
        list = new CustomArrayList<>();
        list.add("String 0");
        list.add("String 1");
        list.add("String 2");
        list.add("String 3");
        list.add("String 4");
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
