package ru.job4j.condition;

/**
 * Класс точка с координатами X и Y.
 * @author Alexey Makarov
 * @since 0.1
 * @version 0.1
 */
public class Point {
    private int x;
    private int y;
    private int z;

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
     * Создаёт новый экземпляр класса Point с заданными координатами.
     * @param x координата по оси X.
     * @param y координата по оси Y.
     * @param z координата по оси Z.
     */
    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Метод находящий расстояние от одной точки к другой.
     * @param that конечная точка.
     * @return расстояние.
     */
    public double distanceTo(Point that) {
        return Math.sqrt(
                Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2)
        );
    }

    public double distanceTo3d(Point that) {
        return Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2) + Math.pow(this.z - that.z, 2));
    }

    /**
     * Метод выдает информацию о координатах точки в консоль.
     */
    public void info() {
        System.out.println(String.format("Point[%s, %s]", this.x, this.y));
    }
}
