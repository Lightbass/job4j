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
        assertThat(shuffle.isShuffled2("папа", "аапп"), is(true));
        assertThat(shuffle.isShuffled3("папа", "аапп"), is(true));
        assertThat(shuffle.isShuffled4("папа", "аапп"), is(true));
    }

    @Test
    public void whenWordsDifferentThenFalse() {
        Map<String, String> map = shuffle.isShuffled(new String[]{"мама", "мамаа", "мамааа", "мммаа", "ммпаа"});
        assertThat(map.get("аамм"), is("мама"));
        assertThat(map.get("ааамм"), is("мамаа"));
        assertThat(map.get("аааамм"), is("мамааа"));
        assertThat(map.get("ааммм"), is("мммаа"));
        assertThat(map.get("ааммп"), is("ммпаа"));
        assertThat(shuffle.isShuffled2("папа", "ааап"), is(false));
        assertThat(shuffle.isShuffled2("папа", "ааппаа"), is(false));
        assertThat(shuffle.isShuffled2("папа", "папааа"), is(false));
        assertThat(shuffle.isShuffled3("папа", "аппп"), is(false));
        assertThat(shuffle.isShuffled3("папа", "ааппаа"), is(false));
        assertThat(shuffle.isShuffled3("папа", "папааа"), is(false));
        assertThat(shuffle.isShuffled4("папа", "аппп"), is(false));
        assertThat(shuffle.isShuffled4("папа", "ааппаа"), is(false));
        assertThat(shuffle.isShuffled4("папа", "папааа"), is(false));
    }
}
