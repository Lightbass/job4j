package ru.job4j.set;

import org.junit.Test;
import org.junit.Before;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса SimpleSet.
 * @author Alexey Makarov
 * @since 20.08.18
 * @version 0.1
 */
public class SimpleSetTest {

    @Test
    public void whenAddsOnlyDuplicateThenOneElementInSet() {
        SimpleSet<String> set = new SimpleSet();
        set.add("One");
        set.add("One");
        set.add("One");
        set.add("One");
        set.add("One");
        Iterator<String> iter = set.iterator();
        assertThat(iter.next(), is("One"));
        assertThat(iter.hasNext(), is(false));
    }

    @Test
    public void whenAddsDuplicateThenLeftOnlyOneOfThem() {
        SimpleSet<String> set = new SimpleSet();
        set.add("One");
        set.add("Two");
        set.add("Three");
        set.add("Three");
        set.add("Three");
        set.add("Four");
        set.add("Four");
        set.add("Four");
        set.add("Five");
        Iterator<String> iter = set.iterator();
        assertThat(iter.next(), is("One"));
        assertThat(iter.next(), is("Two"));
        assertThat(iter.next(), is("Three"));
        assertThat(iter.next(), is("Four"));
        assertThat(iter.next(), is("Five"));
        assertThat(iter.hasNext(), is(false));
    }
}
