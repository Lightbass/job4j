package ru.job4j.condition;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * JUnit тест класса DummyBot
 * @author Alexey Makarov
 * @since 31.07.18
 * @version 0.1
 */
public class DummyBotTest {
    @Test
    public void whenGreetBot() {
        DummyBot db = new DummyBot();
        String str = "Привет, Бот.";
        String result = db.answer(str);
        assertThat(result, is("Привет, умник."));
    }
    @Test
    public void whenByeBot() {
        DummyBot db = new DummyBot();
        String str = "Пока.";
        String result = db.answer(str);
        assertThat(result, is("До скорой встречи."));
    }
    @Test
    public void whenUnknownBot() {
        DummyBot db = new DummyBot();
        String str = "Как дела?";
        String result = db.answer(str);
        assertThat(result, is("Это ставит меня в тупик. Спросите другой вопрос."));
    }
}
