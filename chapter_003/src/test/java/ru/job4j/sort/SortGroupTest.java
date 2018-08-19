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
    public void whenStringWith3GroupsAscSortThenSevenResultGroupsSort() {
        SortGroup sort = new SortGroup();
        String[] input = {"K2", "K1\\SK1\\SSK2\\SSSK3", "K2\\SK1\\SSK1"};
        String[] answer = {"K1", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK2\\SSSK3", "K2", "K2\\SK1", "K2\\SK1\\SSK1"};
        assertThat(sort.ascSort(input), is(answer));
    }

    @Test
    public void whenStringWith7GroupsAscSortThenNineResultGroupsSort() {
        SortGroup sort = new SortGroup();
        String[] input = {"K2\\SK1\\SSK2", "K2\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K2", "K2\\SK1", "K1\\SK1\\SSK1", "K1\\SK2"};
        String[] answer = {"K1", "K1\\SK1", "K1\\SK1\\SSK1",
            "K1\\SK1\\SSK2", "K1\\SK2", "K2", "K2\\SK1", "K2\\SK1\\SSK1", "K2\\SK1\\SSK2"};
        assertThat(sort.ascSort(input), is(answer));
    }

    @Test
    public void whenStringWith2GroupsDescSortThenSixResultGroupsSort() {
        SortGroup sort = new SortGroup();
        String[] input = {"K1\\SK1\\SSK1", "K2\\SK1\\SSK1"};
        String[] answer = {"K2", "K2\\SK1", "K2\\SK1\\SSK1", "K1", "K1\\SK1", "K1\\SK1\\SSK1"};
        assertThat(sort.descSort(input), is(answer));
    }

    @Test
    public void whenStringWith9GroupsDescSortThenValidSort() {
        SortGroup sort = new SortGroup();
        String[] input = {"K1\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1",
                "K1\\SK1\\SSK2", "K1", "K2", "K2\\SK1", "K1\\SK1\\SSK1", "K1\\SK2"};
        String[] answer = {"K2", "K2\\SK1", "K2\\SK1\\SSK2", "K2\\SK1\\SSK1", "K1",
                "K1\\SK2", "K1\\SK1", "K1\\SK1\\SSK2", "K1\\SK1\\SSK1"};
        assertThat(sort.descSort(input), is(answer));
    }
}
