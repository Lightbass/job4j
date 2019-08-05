package ru.job4j.loop;

/**
 * Класс для вычисления протеиновой диеты.
 * @author Alexey Makarov
 * @since 05.08.19
 * @version 0.1
 */
public class Fitness {

    /**
     * Метод вычисляет кол-во месяцев, которое необходимо, чтобы один обогнал другого в тяге.
     * @param ivan сколько кг тянет Иван.
     * @param nik сколько кг тянет Николай.
     * @return сколько нужно месяцев, чтобы обогнать.
     */
    public int calc(int ivan, int nik) {
        int month = 0;
        while (ivan <= nik) {
            ivan *= 3;
            nik *= 2;
            month++;
        }
        return month;
    }
}
