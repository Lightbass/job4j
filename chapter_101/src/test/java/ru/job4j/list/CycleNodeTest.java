package ru.job4j.list;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса CycleNode.
 * @author Alexey Makarov
 * @since 17.08.18
 * @version 0.1
 */
public class CycleNodeTest {
    CycleNode cycle;
    Node n1;
    Node n2;
    Node n3;
    Node n4;

    @Before
    public void prepare() {
        cycle = new CycleNode();
        n1 = new Node();
        n2 = new Node();
        n3 = new Node();
        n4 = new Node();
    }

    @Test
    public void whenListIsNotCycleThenFalse() {
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        assertThat(cycle.hasCycle(n1), is(false));
    }

    @Test
    public void whenListIsCycleThenTrue() {
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n1;
        assertThat(cycle.hasCycle(n1), is(true));
    }

    @Test
    public void whenListIsCycleInMiddleThenTrue() {
        n1.next = n2;
        n2.next = n3;
        n3.next = n2;
        assertThat(cycle.hasCycle(n1), is(true));
    }
}
