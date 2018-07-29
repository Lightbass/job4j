package ru.job4j.calculator;

/**
 * Класс для выполнения математических операций.
 * @author Alexey Makarov
 * @since 29.07.18
 * @version 0.1
 */
public class Calculator {
    /**
     * Результат вычислений одного из методов
     */
    private double result;

    /**
     * Сложение.
     * @param first первый аргумент.
     * @param second второй аргумент.
     */
    public void add(double first, double second) {
        result = first + second;
    }

    /**
     * Вычитание.
     * @param first первый аргумент.
     * @param second второй аргумент.
     */
    public void sub(double first, double second) {
        result = first - second;
    }

    /**
     * Умножение.
     * @param first первый аргумент.
     * @param second второй аргумент.
     */
    public void mul(double first, double second) {
        result = first * second;
    }

    /**
     * Деление.
     * @param first первый аргумент.
     * @param second второй аргумент.
     */
    public void div(double first, double second) {
        result = first / second;
    }

    /**
     * Метод возврашает результат математической операции
     * последнего вызванного метода.
     * @return результат деления.
     */
    public double getResult() {
        return result;
    }
}
