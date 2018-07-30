package ru.job4j.condition;

/**
 * Класс точка с координатами X и Y.
 * @author Alexey Makarov
 * @since 30.07.2018
 * @version 0.1
 */
public class Point {
    private int x;
    private int y;

    /**
     * Создаёт новый экземпляр класса Point с заданными координатами.
     * @param x координата по оси X.
     * @param y координата по оси Y.
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Метод находящий расстояние от одной точки к другой.
     * @param that конечная точка.
     * @return расстояние.
     */
    public double distanceTo(Point that) {
        return Math.sqrt(
                Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.x, 2)
        );
    }
}
