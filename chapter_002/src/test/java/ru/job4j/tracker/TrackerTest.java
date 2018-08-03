package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса Tracker.
 * @author Alexey Makarov
 * @since 03.08.18
 * @version 0.1
 */
public class TrackerTest {

    /**
     * Проверка вывода одного элемента из массива выдаваемого методом findAll().
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "testDescription", 123L);
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    /**
     * Проверка вывода всех добавленных элементов из массива выдаваемого методом findAll().
     */
    @Test
    public void whenAddNewItemsThenTrackerHasSameItems() {
        Tracker tracker = new Tracker();
        Item[] item = new Item[] {new Item("test1", "test111", 123L),
                new Item("test2", "test222", 123L),
                new Item("test3", "test333", 123L)};
        tracker.add(item[0]);
        tracker.add(item[1]);
        tracker.add(item[2]);
        assertThat(tracker.findAll(), is(item));
    }

    /**
     * Проверка вывода всех добавленных элементов из массива выдаваемого методом findAll().
     */
    @Test
    public void whenAddNewItemsThenFindAllByName() {
        Tracker tracker = new Tracker();
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
        assertThat(tracker.findByName("item1")[0].getDescription(), is(item[0].getDescription()));
        assertThat(tracker.findByName("item2")[0].getDescription(), is(item[1].getDescription()));
        assertThat(tracker.findByName("item3")[0].getDescription(), is(item[2].getDescription()));
        assertThat(tracker.findByName("item4"), is(item1));
    }

    /**
     * Проверка удаления заявок в трекере и их вывод.
     */
    @Test
    public void whenDeleteItemThenReturnRemaining() {
        Tracker tracker = new Tracker();
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
        Item[] answer = {items[1], items[2], items[4]};
        tracker.delete(items[0].getId());
        tracker.delete(items[3].getId());
        Item[] result = tracker.findAll();
        assertThat(result, is(answer));
    }

    /**
     * Проверка замены заявки в трекере.
     */
    @Test
    public void whenReplaceNameThenReturnNewName() {
        Tracker tracker = new Tracker();
        Item previous = new Item("test1", "testDescription", 123L);
        tracker.add(previous);
        Item next = new Item("test2", "testDescription2", 1234L);
        next.setId(previous.getId());
        tracker.replace(previous.getId(), next);
        assertThat(tracker.findById(previous.getId()).getName(), is("test2"));
    }
}
