package ru.job4j.tracker.input;

/**
 * Реализация интерфейса Input.
 * @author Alexey Makarov
 * @since 05.08.2018
 * @version 0.1
 */
public class StubInput implements Input {

    /**
     * Нужные для теста ответы.
     */
    private String[] answers;

    /**
     * Позиция в массиве.
     */
    private int position = 0;

    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * Реализация метода ask для теста без пользовательского ввода.
     * @param question Вывод запроса от программы в консоль.
     * @return ответ пользователя.
     */
    public String ask(String question) {
        return answers[position++];
    }

}
