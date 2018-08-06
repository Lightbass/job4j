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
    private final String[] value;

    /**
     * Позиция в массиве.
     */
    private int position = 0;

    /**
     * Конструктор инициализирующий последовательность тестового ввода.
     * @param value массив из комманд для меню.
     */
    public StubInput(String[] value) {
        this.value = value;
    }

    /**
     * Реализация метода ask для теста без пользовательского ввода.
     * @param question Вывод запроса от программы в консоль.
     * @return ответ пользователя.
     */
    public String ask(String question) {
        return value[position++];
    }

}
