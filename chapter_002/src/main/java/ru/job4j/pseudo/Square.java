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

    /**
     * Нарисовать квадрат в строке.
     * @return строка.
     */
    @Override
    public String draw() {
        return this.loopBy(height, height, (i, j) -> (i == 0) || (j == 0) || (i == height - 1) || (j == height - 1));
    }
}
