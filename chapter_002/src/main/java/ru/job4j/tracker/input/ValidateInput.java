package ru.job4j.tracker.input;

import ru.job4j.tracker.MenuOutException;

import java.util.*;

/**
 * Реализация интерфейса Input.
 * @author Alexey Makarov
 * @since 05.08.2018
 * @version 0.1
 */
public class ValidateInput extends ConsoleInput {

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
                value = super.ask(question, range);
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
