package ru.job4j.coffee;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.Arrays;

/**
 * Класс для рассчета сдачи.
 * @author Alexey Makarov
 * @since 03.08.18
 * @version 0.1
 */
public class CoffeeMachine {

    /**
     * Номиналы монет.
     */
    private int[] cc = {10, 5, 2, 1};

    /**
     * Метод выдает сдачу автомата в массиве.
     * @param value кол-во денежных средств закинутых в автомат.
     * @param price цена товара.
     * @return массив ассортимента монет, которые составят сдачу.
     * @throws RuntimeException
     */
    public int[] changes(int value, int price) throws RuntimeException {

        int[] result = new int[0];
        value = value - price;
        if (value < 0) {
            throw new RuntimeException("Not enough money");
        }
        for (int n = 0; n < cc.length; n++) {
            result = changeOnes(result, cc[n], value / cc[n]);
            value %= cc[n];
        }
        return result;
    }

    /**
     * Метод для добавления в массив одинаковых чисел.
     * @param masOrig массив, который требуется расширить.
     * @param coinValue число, которое будет добавляться в каждый довый элемент массива.
     * @param count количество добавляемых элементов.
     * @return массив с дополненными одинаковыми числами.
     */
    private int[] changeOnes(int[] masOrig, int coinValue, int count) {
        int origLength = masOrig.length;
        masOrig = Arrays.copyOf(masOrig, origLength + count);
        Arrays.fill(masOrig, origLength, masOrig.length, coinValue);
        return masOrig;
    }
}
