package ru.job4j.trackerbase.input;

/**
 * Интерфейс для консольного ввода и вывода.
 * @author Alexey Makarov
 * @since 05.08.2018
 * @version 0.1
 */
public interface Input {

    /**
     * Вывод строки для запроса ввода.
     * @param question запрос.
     * @return ответ.
     */
    String ask(String question);
    int ask(String question, int[] range);
}
