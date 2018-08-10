package ru.job4j.search;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса PriorityQueue.
 * @author Alexey Makarov
 * @since 09.08.18
 * @version 0.1
 */
public class PriorityQueueTest {

    /**
     * Когда берем из очереди самую первую по приоритету задачу.
     */
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        Task result = queue.take();
        assertThat(result.getDesc(), is("urgent"));
    }

    /**
     * Когда берем из очереди четвертую(последнюю) задачу.
     */
    @Test
    public void whenLowerPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        queue.put(new Task("lowest", 10));
        queue.take();
        queue.take();
        queue.take();
        Task result = queue.take();
        assertThat(result.getDesc(), is("lowest"));
    }

    /**
     * Когда берем из очереди все элементы.
     */
    @Test
    public void whenRandomPriorityCheckValid() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        queue.put(new Task("lowest", 10));
        queue.put(new Task("lower", 7));
        assertThat(queue.take().getDesc(), is("urgent"));
        assertThat(queue.take().getDesc(), is("middle"));
        assertThat(queue.take().getDesc(), is("low"));
        assertThat(queue.take().getDesc(), is("lower"));
        assertThat(queue.take().getDesc(), is("lowest"));

    }
}