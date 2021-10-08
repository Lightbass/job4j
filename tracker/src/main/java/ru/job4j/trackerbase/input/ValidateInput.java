package ru.job4j.trackerbase.input;

import ru.job4j.trackerbase.MenuOutException;

/**
 * Реализация интерфейса Input.
 * @author Alexey Makarov
 * @since 05.08.2018
 * @version 0.1
 */
public class ValidateInput implements Input {

    private Input input;

    public ValidateInput(Input input) {
        this.input = input;
    }

    /**
     * Реализация метода ask для безопасного пользовательского ввода числа из заданного массива.
     * @param question запрос.
     * @return
     */
    @Override
    public String ask(String question) {
        return this.input.ask(question);
    }

    /**
     * Реализация метода ask для безопасного пользовательского ввода числа из заданного массива.
     * @param question вывод запроса от программы в консоль.
     * @param range массив возможных значений.
     * @return ответ пользователя.
     */
    @Override
    public int ask(String question, int[] range) {
        int value = -1;
        boolean invalid = true;
        do {
            try {
                value = input.ask(question, range);
                invalid = false;
            } catch (MenuOutException nfe) {
                System.out.println("------------ Пожалуйста, введите значение из диапазона меню --------------");
            } catch (NumberFormatException moe) {
                System.out.println("------------ Пожалуйста, введите корректное значение --------------");
            }
        } while (invalid);
        return value;
    }

}
