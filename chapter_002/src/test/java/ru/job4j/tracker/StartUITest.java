package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import ru.job4j.tracker.data.Item;
import ru.job4j.tracker.data.Tracker;
import ru.job4j.tracker.input.*;
import org.junit.Test;
import ru.job4j.tracker.input.StubInput;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

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
     * Родной для текущей ОС переход на следующую строку.
     */
    private final String ls = System.lineSeparator();

    /**
     * Поле содержит дефолтный вывод в консоль.
     */
    private final PrintStream stdout = System.out;

    /**
     * Буфер для результата.
     */
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();


    @Before
    public void beginTest() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(out));
    }

    @After
    public void afterTest() {
        System.setOut(stdout);
        System.out.println("execute after method");
    }

    /**
     * Тест StartUI на вывод в консоль двух заявок.
     */
    @Test
    public void whenUserSelectShowAllItems() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"1", "6"});
        Item item1 = tracker.add(new Item("test name1", "desc1"));
        Item item2 = tracker.add(new Item("test name2", "desc2"));
        StartUI sui = new StartUI(input, tracker);
        sui.init();
        StringBuilder sb = new StringBuilder();
        sb.append("------------ Заявки в трекере --------------").append(ls).append(ls).append(item1.toString())
                .append(ls).append(ls).append(item2.toString());
        assertThat(new String(out.toByteArray()).contains(sb.toString()), is(true));
    }

    /**
     * Тест StartUI на вывод в консоль заявки, найденной по ID.
     */
    @Test
    public void whenFindItemByID() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("Test item", "description"));
        Input input = new StubInput(new String[]{"4", "" + item1.getId(), "6"});
        StartUI sui = new StartUI(input, tracker);
        sui.init();
        StringBuilder sb = new StringBuilder();
        sb.append(item1.toString());
        assertThat(new String(out.toByteArray()).contains(sb.toString()), is(true));
    }

    /**
     * Тест StartUI на вывод в консоль заявки, найденной по имени.
     */
    @Test
    public void whenFindItemByName() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("Test item", "description1"));
        Item item2 = tracker.add(new Item("Test item", "description2"));
        Input input = new StubInput(new String[]{"5", "Test item", "6"});
        StartUI sui = new StartUI(input, tracker);
        sui.init();
        StringBuilder sb = new StringBuilder();
        sb.append(item1.toString()).append(ls).append(ls).append(item2.toString());
        assertThat(new String(out.toByteArray()).contains(sb.toString()), is(true));
    }

    /**
     * Тест StartUI на добавление заявки.
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        Input input = new StubInput(new String[]{"0", "test name", "desc", "6"});
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().get(0).getName(), is("test name"));
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
        assertThat(tracker.findAll().isEmpty(), is(true));
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
     * 8) Попытка указать неверный пункт меню, букву.
     * 9) Попытка указать неверный пункт меню, цифру.
     */
    @Test
    public void whenTryToThrowExceptionThenOK() {
        Tracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test name1", "desc1"));
        Item item2 = tracker.add(new Item("test name2", "desc2"));
        Item item3 = tracker.add(new Item("test name3", "desc3"));
        Item item4 = tracker.add(new Item("test name4", "desc4"));
        Item item5 = tracker.add(new Item("test name5", "desc5"));
        Input input = new ValidateInput(new StubInput(new String[]{"2", "-1",
                "3", "-1",
                "4", "-1",
                "5", "null",
                "2", "0.00",
                "3", "0.00",
                "4", "0.00",
                "ыв", "1",
                "7", "1",
                "6"}));
        new StartUI(input, tracker).init();
        assertThat(tracker.findAll().size(), is(5));
    }

}
