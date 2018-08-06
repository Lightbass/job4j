package ru.job4j.tracker;

import java.util.Scanner;

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

}
