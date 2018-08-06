package ru.job4j.pseudo;

/**
 * Класс для рисования фигур.
 * @author Alexey Makarov
 * @since 06.08.18
 * @version 0.1
 */
public class Paint {

    /**
     * Метод рисующий фигуру.
     * @param shape вигура.
     */
    public void draw(Shape shape) {
        System.out.print(shape.draw());
    }
}
