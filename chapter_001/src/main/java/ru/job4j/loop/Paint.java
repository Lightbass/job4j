package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 * Класс для рисования пирамиды.
 * @author Alexey Makarov
 * @since 31.07.18
 * @version 0.1
 */
public class Paint {

    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }


    /**
     * Метод рисует пирамиду в правой стороне из символа ^ и пробелов.
     * @param height высота пирамиды.
     * @return текстовый вывод пирамиды.
     */
    public String rightTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= column
        );
    }

    /**
     * Метод рисует пирамиду в левой стороне из символа ^ и пробелов.
     * @param height высота пирамиды.
     * @return текстовый вывод пирамиды.
     */
    public String leftTrl(int height) {
        return this.loopBy(
                height,
                height,
                (row, column) -> row >= height - column - 1
        );
    }

    /**
     * Метод рисует пирамиду из символа ^ и пробелов.
     * @param height высота пирамиды.
     * @return текстовый вывод пирамиды.
     */
    public String pyramid(int height) {
        return this.loopBy(
                height,
                height * 2 - 1,
                (row, column) -> column >= height - row - 1 && column <= height + row - 1
        );
    }
}
