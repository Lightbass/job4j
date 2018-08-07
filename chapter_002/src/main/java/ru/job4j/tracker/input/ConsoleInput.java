package ru.job4j.tracker.input;

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
     * @param question Вывод запроса от программы в консоль.
     * @return ответ пользователя.
     */
    public String ask(String question) {
        System.out.print(question);
        return sc.next();
    }

    /**
     * Реализация метода ask для пользовательского ввода числа из заданного массива.
     * @param question Вывод запроса от программы в консоль.
     * @return ответ пользователя.
     */
    public int ask(String question, List<Integer> range) {
        System.out.print(question);
        int result = -1;
        try {
            Integer scan = Integer.parseInt(sc.next());
            for (int i = 0; i != range.size(); i++) {
                if (range.get(i).equals(scan)) {
                    result = scan;
                    break;
                }
            }
        } catch (NumberFormatException nfe) {
        }
        return result;
    }

}
