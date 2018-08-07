package ru.job4j.tracker.input;

import ru.job4j.tracker.MenuOutException;

import java.util.*;

/**
 * Реализация интерфейса Input.
 * @author Alexey Makarov
 * @since 05.08.2018
 * @version 0.1
 */
public class ConsoleInput implements Input {

    /**
     * Объект класса Scanner для считывания ответа пользователя.
     */
    private Scanner sc = new Scanner(System.in);

    /**
     * Реализация метода ask для пользовательского ввода в консоли.
     * @param question вывод запроса от программы в консоль.
     * @return ответ пользователя.
     */
    public String ask(String question) {
        System.out.print(question);
        return sc.next();
    }

    /**
     * Реализация метода ask для пользовательского ввода числа из заданного массива.
     * @param question вывод запроса от программы в консоль.
     * @param range массив возможных значений.
     * @return ответ пользователя.
     */
    public int ask(String question, int[] range) throws MenuOutException {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Out of menu range.");
        }
    }

}
