package ru.job4j.tracker;

import ru.job4j.tracker.input.*;
import org.junit.Test;
import ru.job4j.tracker.input.StubInput;
import ru.job4j.tracker.start.StartUI;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса StartUI.
 * @author Alexey Makarov
 * @since 03.08.18
 * @version 0.1
 */
public class StartUITest {

    /**
     * Тест StartUI на добавление заявки.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll()[0].getName(), is("test name"));
    }

    /**
     * Тест StartUI на редактирование заявки.
     */
    @Test
    public void whenUpdateThenTrackerHasUpdatedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"2", "" + item.getId(), "test replace", "заменили заявку", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findById(item.getId()).getName(), is("test replace"));
    }

    /**
     * Тест StartUI на удаление заявки.
     */
    @Test
    public void whenDeleteThenTrackerHasDeletedValue() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test name", "desc"));
        Input input = new StubInput(new String[]{"3", "" + item.getId(), "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().length, is(0));
    }

    /**
     * Тест StartUI на несуществующие ID и имена заявок:
     * 1) Попытыка редактирования несуществующей заявки.
     * 2) Попытка удаления несуществующей заявки.
     * 3) Попытка нахождения заявки с несуществующим в трекере ID.
     * 4) Попытка нахождения заявки с несуществующим в трекере именем.
     * 5) Попытка указать неверный формат ID при редактировании.
     * 6) Попытка указать неверный формат ID при удалении.
     * 7) Попытка указать неверный формат ID при поиске по ID.
     */
    @Test
    public void whenTryToThrowExceptionThenOK() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test name1", "desc1"));
        Item item2 = tracker.add(new Item("test name2", "desc2"));
        Item item3 = tracker.add(new Item("test name3", "desc3"));
        Item item4 = tracker.add(new Item("test name4", "desc4"));
        Item item5 = tracker.add(new Item("test name5", "desc5"));
        Input input = new StubInput(new String[]{"2", "-1",
                "3", "-1",
                "4", "-1",
                "5", "null",
                "2", "0.00",
                "3", "0.00",
                "4", "0.00",
                "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().length, is(5));
    }

}
