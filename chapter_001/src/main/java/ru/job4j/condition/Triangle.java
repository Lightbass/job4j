package ru.job4j.condition;

/**
 * Класс треугольник.
 * @author Alexey Makarov
 * @since 31.07.18
 * @version 0.1
 */
public class Triangle {
    private Point a;
    private Point b;
    private Point c;

    /**
     * Создание треугольника с указанием трёх точек в пространстве.
     * @param a первая точка.
     * @param b вторая точка.
     * @param c третья точка.
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Вычисление полупериода по длинам сторон.
     * @param ab сторона 1.
     * @param ac сторона 2.
     * @param bc сторона 3.
     * @return
     */
    public double period(double ab, double ac, double bc) {
        return (ab + bc + ac) / 2;
    }

    /**
     * Площадь треугольника.
     *
     * В начале выполнения функции мы устанавливаем значение -1, так как может быть что треугольника нет.
     * Это значение говорит о том. что треугольника нет.
     *
     * @return площадь.
     */
    public double area() {
        double rsl = -1;
        double ab = this.a.distanceTo(this.b);
        double ac = this.a.distanceTo(this.c);
        double bc = this.b.distanceTo(this.c);
        double p = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        }
        return rsl;
    }

    /**
     * Проверка, существует ли треугольник.
     * @param ab сторона a.
     * @param ac сторона c.
     * @param bc сторона b.
     * @return true - если существует.
     */
    public boolean exist(double ab, double ac, double bc) {
        if (ab + ac > bc && ab + bc > ac && ac + bc > ac) {
            return true;
        }
        return false;
    }

}
