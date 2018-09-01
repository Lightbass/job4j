package ru.job4j.exam;

import org.junit.Test;
import org.junit.Before;
import java.util.Map;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса Trie.
 * @author Alexey Makarov
 * @since 01.09.18
 * @version 0.1
 */
public class TrieTest {
    Trie<Integer> trie;

    @Before
    public void prepare() {
        trie = new Trie<>();
        trie.add("One", 1);
        trie.add("Two", 2);
        trie.add("Three", 3);
        trie.add("Four", 4);
        trie.add("Five", 5);
    }

    @Test
    public void whenAddsFiveValuesAndGetItThenOk() {
        assertThat(trie.get("One"), is(1));
        assertThat(trie.get("Two"), is(2));
        assertThat(trie.get("Three"), is(3));
        assertThat(trie.get("Four"), is(4));
        assertThat(trie.get("Five"), is(5));
    }

    @Test
    public void whenTryGetMissingElementsThenNull() {
        assertThat(trie.get("Onee"), nullValue());
        assertThat(trie.get("TTwo"), nullValue());
        assertThat(trie.get("Threee"), nullValue());
        assertThat(trie.get("Fou"), nullValue());
        assertThat(trie.get("Fi"), nullValue());
    }
}
