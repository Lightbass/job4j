package ru.job4j.sort;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса SortGroup.
 * @author Alexey Makarov
 * @since 15.08.18
 * @version 0.1
 */
public class SortGroupTest {

    @Test
    public void whenStringWith3GroupsAscSortThenValidSort() {
        SortGroup sort = new SortGroup();
        String[] input = {"K2", "K1\\SK1\\SSK2", "K2\\SK1\\SSK1"};
        sort.ascSort(input);
        String[] answer = {"K1\\SK1\\SSK2", "K2", "K2\\SK1\\SSK1"};
        assertThat(input, is(answer));
    }

    @Test
    public void whenStringWith9GroupsAscSortThenValidSort() {
        SortGroup sort = new SortGroup();
        String[] input = {"K2\\SK1\\SSK2", "K1\\SK1", "K2\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K1", "K2", "K2\\SK1", "K1\\SK1\\SSK1", "K1\\SK2"};
        sort.ascSort(input);
        String[] answer = {"K1", "K1\\SK1", "K1\\SK1\\SSK1",
            "K1\\SK1\\SSK2", "K1\\SK2", "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        assertThat(input, is(answer));
    }

    @Test
    public void whenStringWith3GroupsDescSortThenValidSort() {
        SortGroup sort = new SortGroup();
        String[] input = {"K1\\SK1\\SSK1", "K2", "K1\\SK1"};
        sort.descSort(input);
        String[] answer = {"K2", "K1\\SK1", "K1\\SK1\\SSK1"};
        assertThat(input, is(answer));
    }

    @Test
    public void whenStringWith9GroupsDescSortThenValidSort() {
        SortGroup sort = new SortGroup();
        String[] input = {"K1\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K1", "K2", "K2\\SK1", "K1\\SK1\\SSK1", "K1\\SK2"};
        sort.descSort(input);
        String[] answer = {"K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1", "K1",
                "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"};
        assertThat(input, is(answer));
    }
}
