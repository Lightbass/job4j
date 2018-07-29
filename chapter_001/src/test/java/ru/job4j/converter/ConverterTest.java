package ru.job4j.converter;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit класс для тестирования методов класса Converter.
 * @author Alexey Makarov
 * @since 29.07.2018
 * @version 0.1
 */
public class ConverterTest {

    /**
     * Тест метода конвертации рубля в доллар.
     */
    @Test
    public void when60RubleToDollarThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToDollar(60);
        assertThat(result, is(1));
    }

    /**
     * Тест метода конвертации рубля в евро.
     */
    @Test
    public void when70RubleToEuroThen1() {
        Converter converter = new Converter();
        int result = converter.rubleToEuro(70);
        assertThat(result, is(1));
    }

    /**
     * Тест метода конвертации доллара в рубли.
     */
    @Test
    public void when5DollarsToRubleThen300() {
        Converter converter = new Converter();
        int result = converter.dollarToRuble(5);
        assertThat(result, is(300));
    }

    /**
     * Тест метода конвертации евро в рубли.
     */
    @Test
    public void when5EuroToRubleThen350() {
        Converter converter = new Converter();
        int result = converter.euroToRuble(5);
        assertThat(result, is(350));
    }
}