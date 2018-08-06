package ru.job4j.pseudo;

import java.util.function.BiPredicate;

/**
 * Интерфейс - форма.
 * @author Alexey Makarov
 * @since 06.08.18
 * @version 0.1
 */
public interface Shape {

    /**
     * Нарисовать фигуру в строке.
     * @return строка.
     */
    String draw();

    /**
     * Предикат для вывода символов в матрице.
     * @param height высота.
     * @param weight ширина.
     * @param predict условие.
     * @return
     */
    default String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
        StringBuilder screen = new StringBuilder();
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (predict.test(row, column)) {
                    screen.append('+');
                } else {
                    screen.append(' ');
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }
}
