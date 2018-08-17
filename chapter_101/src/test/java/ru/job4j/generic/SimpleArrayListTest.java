package ru.job4j.generic;

import org.junit.Test;
import org.junit.Before;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса SimpleArrayList.
 * @author Alexey Makarov
 * @since 17.08.18
 * @version 0.1
 */
public class SimpleArrayListTest {

    private SimpleArrayList<Integer> list;

    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenAddThreeElementsThenUseGetTwoResultOne() {
        assertThat(list.get(2), is(1));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void whenDeleteTwoElementsThenGetZeroElementResultOne() {
        list.delete();
        list.delete();
        assertThat(list.get(0), is(1));
        assertThat(list.getSize(), is(1));
    }

    @Test
    public void whenDeleteOneElementThenGetZeroElementResultTwo() {
        list.delete();
        assertThat(list.get(0), is(2));
        assertThat(list.getSize(), is(2));
    }
}