package ru.job4j.tracker;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.*;
import ru.job4j.tracker.data.*;
import ru.job4j.tracker.input.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * JUnit тест класса ValidateInput.
 * @author Alexey Makarov
 * @since 03.08.18
 * @version 0.1
 */
public class ValidateInputTest {

    /**
     * Поле содержит дефолтный вывод в консоль.
     */
    private final PrintStream stdout = System.out;

    /**
     * Буфер для результата.
     */
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Before
    public void beginTest() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(out));
    }

    @After
    public void afterTest() {
        System.setOut(stdout);
        System.out.println("execute after method");
    }

    /**
     * Проверка на неправильный формат ввода в меню.
     */
    @Test
    public void whenInvalidInput() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"invalid", "1"})
        );
        input.ask("Enter", new int[] {1});
        assertThat(
                this.out.toString(),
                is(
                        String.format("------------ Пожалуйста, введите корректное значение --------------"
                        + System.lineSeparator())
                )
        );
    }

    /**
     * Проверка на неправильный выбор пункта в меню.
     */
    @Test
    public void whenInvalidNumber() {
        ValidateInput input = new ValidateInput(
                new StubInput(new String[] {"2", "1"})
        );
        input.ask("Enter", new int[] {1});
        assertThat(
                this.out.toString(),
                is(
                        String.format("------------ Пожалуйста, введите значение из диапазона меню --------------"
                                + System.lineSeparator())
                )
        );
    }
}