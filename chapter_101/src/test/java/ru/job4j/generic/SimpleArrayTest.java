package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса SimpleArray.
 * @author Alexey Makarov
 * @since 16.08.18
 * @version 0.1
 */
public class SimpleArrayTest {

    @Test
    public void whenFiveElementsInListIteratorThenGetIt() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.add("One");
        array.add("Two");
        array.add("Three");
        array.add("Four");
        array.add("Five");
        assertThat(array.get(0), is("One"));
        assertThat(array.get(1), is("Two"));
        assertThat(array.get(2), is("Three"));
        assertThat(array.get(3), is("Four"));
        assertThat(array.get(4), is("Five"));
    }

    @Test
    public void whenFiveElementsInListIteratorThenOk() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.add("One");
        array.add("Two");
        array.add("Three");
        array.add("Four");
        array.add("Five");
        Iterator<String> it = array.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("One"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Two"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Three"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Four"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Five"));
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenFiveElementsInListIteratorDeleteOneAndSetOneThenOk() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.add("One");
        array.add("Two");
        array.add("Three");
        array.add("Four");
        array.add("Five");
        array.delete(2);
        array.set(3, "Three");
        Iterator<String> it = array.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("One"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Two"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Four"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Three"));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorExceptionThenExpected() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.add("One");
        array.add("Two");
        Iterator<String> it = array.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("One"));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is("Two"));
        assertThat(it.hasNext(), is(false));
        it.next();
    }

    @Test(expected = IllegalStateException.class)
    public void whenArrayFullSizeExceptionThenExpected() {
        SimpleArray<String> array = new SimpleArray<>(2);
        array.add("One");
        array.add("Two");
        array.add("Three");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenArrayDeleteThenException() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.add("One");
        array.add("Two");
        array.add("Three");
        array.delete(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenArraySetThenException() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.add("One");
        array.add("Two");
        array.add("Three");
        array.set(3, "Four");
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenArrayGetThenException() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.add("One");
        array.add("Two");
        array.add("Three");
        array.get(3);
    }
}
