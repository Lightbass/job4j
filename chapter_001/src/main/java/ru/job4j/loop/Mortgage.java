package ru.job4j.loop;

/**
 * Класс для вычисления количества лет необходимых для погашения кредита.
 * @author Alexey Makarov
 * @since 0.1
 * @version 0.1
 */
public class Mortgage {

    /**
     * Метод вычисляет кол-во простых чисел.
     * @param amount сумма кредита.
     * @param monthly сколько можем отдавать в месяц.
     * @param percent процент годовых.
     * @return количество лет, необходимое для погашения кредита.
     */
    public int year(double amount, int monthly, double percent) {
        int year = 0;
        do {
            amount = amount * (1 + percent / 100) - monthly * 12;
            year++;
        } while (amount > 0);
        return year;
    }
}
