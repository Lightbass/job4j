package ru.job4j.exam;

import org.junit.Test;
import org.junit.Before;
import java.util.Map;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса StringShuffle.
 * @author Alexey Makarov
 * @since 30.08.18
 * @version 0.1
 */
public class StringShuffleTest {

    StringShuffle shuffle;

    @Before
    public void prepare() {
        shuffle = new StringShuffle();
    }

    @Test
    public void whenWordsShuffledThenTrue() {
        Map<String, String> map = shuffle.isShuffled(new String[]{"мама", "папка", "амам", "ммаа", "амма", "дичь",
                "паапк", "розовый", "вйоозыр", "три"});
        assertThat(map.get("аамм"), is("мама амам ммаа амма"));
        assertThat(map.get("аакпп"), is("папка паапк"));
        assertThat(map.get("дичь"), is("дичь"));
        assertThat(map.get("взйооры"), is("розовый вйоозыр"));
        assertThat(map.get("ирт"), is("три"));
    }

    @Test
    public void whenWordsShuffled2ThenTrue() {
        assertThat(shuffle.isShuffled2("папа", "аапп"), is(true));
    }

    @Test
    public void whenWordsShuffled3ThenTrue() {
        assertThat(shuffle.isShuffled3("папа", "аапп"), is(true));
    }

    @Test
    public void whenWordsShuffled4ThenTrue() {
        assertThat(shuffle.isShuffled4("папа", "аапп"), is(true));
    }

    @Test
    public void whenWordsShuffled5ThenTrue() {
        assertThat(shuffle.containsAll("папа", "аапп"), is(true));
    }

    @Test
    public void whenWordsDifferentThenFalse() {
        Map<String, String> map = shuffle.isShuffled(new String[]{"мама", "мамаа", "мамааа", "мммаа", "ммпаа"});
        assertThat(map.get("аамм").equals("мама")
                && map.get("ааамм").equals("мамаа")
                && map.get("аааамм").equals("мамааа")
                && map.get("ааммм").equals("мммаа")
                && map.get("ааммп").equals("ммпаа"), is(true));
    }

    @Test
    public void whenWordsDifferent2ThenFalse() {
        assertThat(shuffle.isShuffled2("папа", "ааап")
                && shuffle.isShuffled2("папа", "ааппаа")
                && shuffle.isShuffled2("папа", "папааа"), is(false));
    }

    @Test
    public void whenWordsDifferent3ThenFalse() {
        assertThat(shuffle.isShuffled3("папа", "аппп")
                && shuffle.isShuffled3("папа", "ааппаа")
                && shuffle.isShuffled3("папа", "папааа"), is(false));
    }

    @Test
    public void whenWordsDifferent4ThenFalse() {
        assertThat(shuffle.isShuffled4("папа", "аппп")
                && shuffle.isShuffled4("папа", "ааппаа")
                && shuffle.isShuffled4("папа", "папааа"), is(false));
    }

    @Test
    public void whenWordsDifferent5ThenFalse() {
        assertThat(shuffle.containsAll("папа", "аппп")
                && shuffle.containsAll("папа", "ааппаа")
                && shuffle.containsAll("папа", "папааа"), is(false));
    }
}
