package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса CustomMap.
 * @author Alexey Makarov
 * @since 23.08.18
 * @version 0.1
 */
public class CustomMapTest {
    CustomMap<String, String> map;
    Iterator<String> iterK;
    Iterator<String> iterV;
    @Before
    public void prepare() {
        map = new CustomMap<>(4, 0.5f);
        map.put("String 0", "Value 0");
        map.put("String 1", "Value 1");
        map.put("String 2", "Value 2");
        map.put("String 3", "Value 3");
        map.put("String 4", "Value 4");
        map.put("String 5", "Value 5");
        map.put("String 6", "Value 6");
        map.put("String 7", "Value 7");
        map.put("String 8", "Value 8");
        map.put("String 9", "Value 9");
        iterK = map.keys();
        iterV = map.values();
    }

    @Test
    public void whenGetValuesOfKeysThenOK() {
        assertThat(map.get("String 0"),  is("Value 0"));
        assertThat(map.get("String 1"),  is("Value 1"));
        assertThat(map.get("String 2"),  is("Value 2"));
        assertThat(map.get("String 3"),  is("Value 3"));
        assertThat(map.get("String 4"),  is("Value 4"));
        assertThat(map.get("String 5"),  is("Value 5"));
        assertThat(map.get("String 6"),  is("Value 6"));
        assertThat(map.get("String 7"),  is("Value 7"));
        assertThat(map.get("String 8"),  is("Value 8"));
        assertThat(map.get("String 9"),  is("Value 9"));
    }

    @Test
    public void whenPutSameKeyWithVariousValueThenRewrite() {
        map.put("String 3", "Edited 3");
        assertThat(map.get("String 3"),  is("Edited 3"));
        map.put("String 7", "Edited 7");
        assertThat(map.get("String 7"),  is("Edited 7"));
        map.put("String 0", "Edited 0");
        assertThat(map.get("String 0"),  is("Edited 0"));
        map.put("String 9", "Edited 9");
        assertThat(map.get("String 9"),  is("Edited 9"));
        while (iterK.hasNext()) {
            assertThat(iterK.next(), anyOf(
                    is("String 0"), is("String 1"),
                    is("String 2"), is("String 3"),
                    is("String 4"), is("String 5"),
                    is("String 6"), is("String 7"),
                    is("String 8"), is("String 9")
            ));
            assertThat(iterV.hasNext(), is(true));
            assertThat(iterV.next(), anyOf(
                    is("Edited 0"), is("Value 1"),
                    is("Value 2"), is("Edited 3"),
                    is("Value 4"), is("Value 5"),
                    is("Value 6"), is("Edited 7"),
                    is("Value 8"), is("Edited 9")
            ));
        }
    }

    @Test
    public void whenDeleteKeyThenThisKeyNotFound() {
        map.delete("String 3");
        assertThat(map.get("String 3"),  is(nullValue()));
        map.delete("String 7");
        assertThat(map.get("String 7"),  is(nullValue()));
        map.delete("String 0");
        assertThat(map.get("String 0"),  is(nullValue()));
        map.delete("String 9");
        assertThat(map.get("String 9"),  is(nullValue()));
        iterK = map.keys();
        iterV = map.values();
        while (iterK.hasNext()) {
            assertThat(iterK.next(), anyOf(
                    is("String 1"), is("String 2"),
                    is("String 4"), is("String 5"),
                    is("String 6"), is("String 8")
            ));
            assertThat(iterV.hasNext(), is(true));
            assertThat(iterV.next(), anyOf(
                    is("Value 1"), is("Value 2"),
                    is("Value 4"), is("Value 5"),
                    is("Value 6"), is("Value 8")
            ));
        }
    }

    @Test
    public void whenCheckIteratorThenOk() {
        while (iterK.hasNext()) {
            assertThat(iterK.next(), anyOf(
                    is("String 0"), is("String 1"),
                    is("String 2"), is("String 3"),
                    is("String 4"), is("String 5"),
                    is("String 6"), is("String 7"),
                    is("String 8"), is("String 9")
            ));
            assertThat(iterV.hasNext(), is(true));
            assertThat(iterV.next(), anyOf(
                    is("Value 0"), is("Value 1"),
                    is("Value 2"), is("Value 3"),
                    is("Value 4"), is("Value 5"),
                    is("Value 6"), is("Value 7"),
                    is("Value 8"), is("Value 9")
            ));
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorEndsThenException() {
        for (int i = 0; i != 11; i++) {
            iterV.next();
        }
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenIteratorWorkedAndDataHasChangedThenException() {
        iterV.next();
        map.put("aaa", "aaa");
        iterV.next();
    }
}
