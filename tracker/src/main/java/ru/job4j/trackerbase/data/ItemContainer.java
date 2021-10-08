package ru.job4j.trackerbase.data;

import java.util.List;

public interface ItemContainer {
    Item add(Item item);
    boolean replace(long id, Item item);
    boolean delete(long id);
    List<Item> findAll();
    List<Item> findByName(String key);
    Item findById(long id);


}
