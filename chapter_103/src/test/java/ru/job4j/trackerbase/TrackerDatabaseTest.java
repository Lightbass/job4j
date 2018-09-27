package ru.job4j.trackerbase;

import org.junit.Before;
import ru.job4j.tracker.TrackerTest;
import ru.job4j.tracker.data.Tracker;


/**
 * JUnit тест класса Tracker.
 * @author Alexey Makarov
 * @since 03.08.18
 * @version 0.1
 */
public class TrackerDatabaseTest extends TrackerTest {

    @Override
    @Before
    public void prepare() {
    }

    @Override
    public void whenAddNewItemThenTrackerHasSameItem() {
        try (TrackerDatabase tracker = new TrackerDatabase()) {
            super.tracker = tracker;
            super.whenAddNewItemThenTrackerHasSameItem();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void whenAddNewItemsThenTrackerHasSameItems() {
        try (TrackerDatabase tracker = new TrackerDatabase()) {
            super.tracker = tracker;
            super.whenAddNewItemsThenTrackerHasSameItems();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void whenAddNewItemsThenFindAllByName() {
        try (TrackerDatabase tracker = new TrackerDatabase()) {
            super.tracker = tracker;
            super.whenAddNewItemsThenFindAllByName();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void whenDeleteItemThenReturnRemaining() {
        try (TrackerDatabase tracker = new TrackerDatabase()) {
            super.tracker = tracker;
            super.whenDeleteItemThenReturnRemaining();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void whenReplaceNameThenReturnNewName() {
        try (TrackerDatabase tracker = new TrackerDatabase()) {
            super.tracker = tracker;
            super.whenReplaceNameThenReturnNewName();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
