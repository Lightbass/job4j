package ru.job4j.pseudo;

import java.util.function.BiPredicate;

/**
 * Класс квадрат.
 * @author Alexey Makarov
 * @since 06.08.18
 * @version 0.1
 */
public class Square implements Shape {

    /**
     * Высота квадрата.
     */
    final int height = 5;

    private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
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

    /**
     * Нарисовать квадрат в строке.
     * @return строка.
     */
    @Override
    public String draw() {
        return this.loopBy(height, height, (i, j) -> (i == 0) || (j == 0) || (i == height - 1) || (j == height - 1));
    }
}
