package ru.job4j.pseudo;

import java.util.function.BiPredicate;

/**
 * Класс треугольник.
 * @author Alexey Makarov
 * @since 06.08.18
 * @version 0.1
 */
public class Triangle implements Shape {
    /**
     * Высота треугольника
     */
    final int height = 5;

    /**
     * Нарисовать треугольник в строке.
     * @return строка.
     */
    @Override
    public String draw() {
        return this.loopBy(
                height,
                height * 2 - 1,
                (row, column) -> column == height - row - 1 || column == height + row - 1 || row == (height - 1)
        );
    }

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
}
