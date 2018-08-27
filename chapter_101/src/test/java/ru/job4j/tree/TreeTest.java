package ru.job4j.tree;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * JUnit тест класса RoleStore.
 * @author Alexey Makarov
 * @since 27.08.18
 * @version 0.1
 */
public class TreeTest {
    Tree<Integer> tree = new Tree<>(1);

    @Test
    public void when6ElFindLastThen6() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new Tree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenAddDuplicatesThenOnlyOne() {
        tree.add(1, 1);
        tree.add(1, 2);
        tree.add(2, 3);
        tree.add(1, 3);
        tree.add(3, 4);
        tree.add(3, 1);
        Iterator<Integer> iter = tree.iterator();
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(1));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(2));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(3));
        assertThat(iter.hasNext(), is(true));
        assertThat(iter.next(), is(4));
        assertThat(iter.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIteratorEndsThenException() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 11);
        tree.add(2, 22);
        tree.add(3, 33);
        tree.add(3, 44);
        Iterator<Integer> iter = tree.iterator();
        iter.next();
        iter.next();
        iter.next();
        iter.next();
        iter.next();
        iter.next();
        iter.next();
        assertThat(iter.hasNext(), is(false));
        iter.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenModTreeOnIteratorWorkingThenException() {
        tree.add(1, 222);
        tree.add(1, 333);
        Iterator<Integer> iter = tree.iterator();
        iter.next();
        tree.add(1, 444);
        iter.next();
    }
}