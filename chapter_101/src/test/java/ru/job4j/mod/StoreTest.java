package ru.job4j.mod;

import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;
import ru.job4j.mod.Store.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса User.
 * @author Alexey Makarov
 * @since 20.08.18
 * @version 0.1
 */
public class StoreTest {
    List<Store.User> list1;
    List<Store.User> list2;
    Store store;

    @Before
    public void prepare() {
        list1 = new ArrayList<>();
        list1.add(new User(1, "One"));
        list1.add(new User(2, "Two"));
        list1.add(new User(3, "Three"));
        list1.add(new User(4, "Four"));
        list1.add(new User(5, "Five"));
        list2 = new ArrayList<>(list1);
        store = new Store();
    }

    @Test
    public void whenCompareEditedListThenThreeCreatedOneDeletedTwoEdited() {
        list2.remove(4);
        list2.set(1, new User(2, "TwoTwo"));
        list2.set(2, new User(3, "ThreeThree"));
        list2.add(new User(6, "Six"));
        list2.add(new User(7, "Seven"));
        list2.add(new User(8, "Eight"));
        Info info = store.diff(list1, list2);
        assertThat(info.getCreated(), is(3));
        assertThat(info.getModified(), is(2));
        assertThat(info.getDeleted(), is(1));
    }

    @Test
    public void whenAllDeletedThen5DeletesInInfo() {
        list2.clear();
        Info info = store.diff(list1, list2);
        assertThat(info.getCreated(), is(0));
        assertThat(info.getModified(), is(0));
        assertThat(info.getDeleted(), is(5));
        info = store.diff(list2, list1);
        assertThat(info.getCreated(), is(5));
        assertThat(info.getModified(), is(0));
        assertThat(info.getDeleted(), is(0));
    }

    @Test
    public void whenCreate5ElementsThen5CreatesInInfo() {
        list2.add(new User(6, "Six"));
        list2.add(new User(7, "Seven"));
        list2.add(new User(8, "Eight"));
        list2.add(new User(9, "Nine"));
        list2.add(new User(10, "Ten"));
        Info info = store.diff(list1, list2);
        assertThat(info.getCreated(), is(5));
        assertThat(info.getModified(), is(0));
        assertThat(info.getDeleted(), is(0));
    }

    @Test
    public void whenEdit5ElementsThen5EditInInfo() {
        list2.set(0, new User(1, "OneOne"));
        list2.set(1, new User(2, "TwoTwo"));
        list2.set(2, new User(3, "ThreeThree"));
        list2.set(3, new User(4, "FourFour"));
        list2.set(4, new User(5, "FiveFive"));
        Info info = store.diff(list1, list2);
        assertThat(info.getCreated(), is(0));
        assertThat(info.getModified(), is(5));
        assertThat(info.getDeleted(), is(0));
    }

    @Test
    public void whenNothingModifiedThenZeroes() {
        Info info = store.diff(list1, list2);
        assertThat(info.getCreated(), is(0));
        assertThat(info.getModified(), is(0));
        assertThat(info.getDeleted(), is(0));
    }
}
