package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit класс для теста класса DuplicateTest.
 * @author Alexey Makarov
 * @since 01.08.2018
 * @version 0.1
 */
public class ArrayDuplicateTest {

    /**
     * Проверка на удаление дубликатов.
     */
    @Test
    public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
        String[] input = {"Привет", "Мир", "Как дела", "Мир", "Привет", "Супер", "Мир"};
        String[] result = {"Привет", "Мир", "Как дела", "Супер"};
        ArrayDuplicate ad = new ArrayDuplicate();
        assertThat(ad.remove(input), is(result));
    }
}