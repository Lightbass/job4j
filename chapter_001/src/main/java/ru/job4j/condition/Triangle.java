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
     * @return
     */
    public double area() {
        double rsl = -1; // мы устанавливаем значение -1, так как может быть что треугольника нет. Это значение говорит о том. что треугольника нет.
        double ab = this.a.distanceTo(this.b);
        double ac = this.a.distanceTo(this.c);
        double bc = this.b.distanceTo(this.c);
        double p = this.period(ab, ac, bc);
        if (this.exist(ab, ac, bc)) {
            rsl = Math.sqrt(p * (p - ab) * (p - ac) * (p - bc));
        }
        return rsl;
    }

    public boolean exist(double ab, double ac, double bc) {
        double max = ab > ac ? (ab > bc ? ab : bc) : (ac > bc ? ac : bc);
        double sum2min = (ab < max ? ab : 0) + (ac < max ? ac : 0) + (bc < max ? bc : 0);
        if (sum2min > max) {
            return true;
        }
        return false;
    }

}
