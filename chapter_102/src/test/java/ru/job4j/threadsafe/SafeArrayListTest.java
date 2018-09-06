package ru.job4j.threadsafe;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.list.CustomArrayList;

import java.util.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса SafeArrayList.
 * @author Alexey Makarov
 * @since 06.09.18
 * @version 0.1
 */
public class SafeArrayListTest {

    private Thread add;
    private SafeArrayList<Integer> list;

    /**
     * Класс создает поток для добавления пользователей в хранилище.
     */
    private class ThreadAdd extends Thread {
        private final SafeArrayList<Integer> list;

        private ThreadAdd(final SafeArrayList<Integer> list) {
            this.list = list;
        }

        @Override
        public void run() {
            for (int i = 0; i != 5; i++) {
                list.add(i * 5);
            }
        }
    }

    /**
     * Класс создает поток для прохождения по итератору коллекции.
     */
    private class ThreadGet extends Thread {
        private final Iterator<Integer> iter;
        public List<Integer> result = new LinkedList<>();

        private ThreadGet(final SafeArrayList<Integer> list) {
            iter = list.iterator();
        }

        @Override
        public void run() {
            while (iter.hasNext()) {
                result.add(iter.next());
            }
        }
    }

    @Before
    public void prepare() throws InterruptedException {
        list = new SafeArrayList<>(new CustomArrayList<>());
        add = new ThreadAdd(list);
        add.start();
        add.join();
    }

    @Test
    public void whenAddsFiveElementsAndGetItWithIteratorThenOk() throws InterruptedException {
        ThreadGet iter = new ThreadGet(list);
        iter.start();
        list.delete(0);
        list.add(25);
        list.add(30);
        iter.join();
        assertThat(iter.result.toString(), is("[0, 5, 10, 15, 20]"));
    }

    @Test
    public void whenAddsFiveElementsAndAddsMoreThenOk() throws InterruptedException {
        add = new ThreadAdd(list);
        add.start();
        list.delete(0);
        list.delete(3);
        list.delete(1);
        add.join();
        ThreadGet iter = new ThreadGet(list);
        iter.start();
        iter.join();
        assertThat(iter.result.toString(), is("[5, 15, 0, 5, 10, 15, 20]"));
    }
}
