package ru.job4j.coffee;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * JUnit тест класса CoffeeMachine.
 * @author Alexey Makarov
 * @since 09.08.18
 * @version 0.1
 */
public class CoffeeMachineTest {
    /**
     * Тест 109 рублей закинули в автомат, 10 стоимость.
     */
    @Test
    public void whenPut99then105211() {
        CoffeeMachine cm = new CoffeeMachine();
        int[] answer = new int[]{10, 10, 10, 10, 10, 10, 10, 10, 10, 5, 2, 2};
        assertThat(cm.changes(109, 10), is(answer));
    }

    /**
     * Тест 50 рублей закинули в автомат, 15 стоимость.
     */
    @Test
    public void whenPut50AndPrice15then1010105() {
        CoffeeMachine cm = new CoffeeMachine();
        int[] answer = new int[]{10, 10, 10, 5};
        assertThat(cm.changes(50, 15), is(answer));
    }

    /**
     * Тест 39 рублей закинули в автомат, 16 стоимость.
     */
    @Test
    public void whenPut39AndPrice16then101021() {
        CoffeeMachine cm = new CoffeeMachine();
        int[] answer = new int[]{10, 10, 2, 1};
        assertThat(cm.changes(39, 16), is(answer));
    }
}
