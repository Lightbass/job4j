package ru.job4j.trackerbase;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.trackerbase.data.Item;
import ru.job4j.trackerbase.data.ItemContainer;
import ru.job4j.trackerbase.data.Tracker;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса Tracker.
 * @author Alexey Makarov
 * @since 03.08.18
 * @version 0.1
 */
public class TrackerTest {
    protected ItemContainer tracker;

    @Before
    public void prepare() {
        tracker = new Tracker();
    }

    /**
     * Проверка вывода одного элемента из массива выдаваемого методом findAll().
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll().get(0), is(item));
    }

    /**
     * Проверка вывода всех добавленных элементов из массива выдаваемого методом findAll().
     */
    @Test
    public void whenAddNewItemsThenTrackerHasSameItems() {
        Item[] item = new Item[] {new Item("test1", "test111", 123L),
                new Item("test2", "test222", 123L),
                new Item("test3", "test333", 123L)};
        tracker.add(item[0]);
        tracker.add(item[1]);
        tracker.add(item[2]);
        List<Item> answer = Arrays.asList(item);
        assertThat(tracker.findAll(), is(answer));
    }

    /**
     * Проверка вывода всех добавленных элементов из массива выдаваемого методом findAll().
     */
    @Test
    public void whenAddNewItemsThenFindAllByName() {
        Item[] item = {new Item("item1", "item111", 123L),
                new Item("item2", "item222", 123L),
                new Item("item3", "item333", 123L)};
        Item[] item1 = {new Item("item4", "item444", 123L),
                new Item("item4", "item555", 123L)};
        tracker.add(item[0]);
        tracker.add(item[1]);
        tracker.add(item[2]);
        tracker.add(item1[0]);
        tracker.add(item1[1]);
        assertThat(tracker.findByName("item1").get(0).getDescription(), is(item[0].getDescription()));
        assertThat(tracker.findByName("item2").get(0).getDescription(), is(item[1].getDescription()));
        assertThat(tracker.findByName("item3").get(0).getDescription(), is(item[2].getDescription()));
        List<Item> answer = Arrays.asList(item1);
        assertThat(tracker.findByName("item4"), is(answer));
    }

    /**
     * Проверка удаления заявок в трекере и их вывод.
     */
    @Test
    public void whenDeleteItemThenReturnRemaining() {
        Item[] items = {new Item("item1", "item111", 123L),
                new Item("item2", "item222", 456L),
                new Item("item3", "item333", 123L),
                new Item("item4", "item444", 123L),
                new Item("item5", "item555", 123L)};
        tracker.add(items[0]);
        tracker.add(items[1]);
        tracker.add(items[2]);
        tracker.add(items[3]);
        tracker.add(items[4]);
        List<Item> answer = Arrays.asList(new Item[]{items[1], items[2], items[4]});
        tracker.delete(items[0].getId());
        tracker.delete(items[3].getId());
        List<Item> result = tracker.findAll();
        assertThat(result, is(answer));
    }

    /**
     * Проверка замены заявки в трекере.
     */
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }
}
