package ru.job4j.array;

import java.util.function.BiPredicate;

/**
 * Класс для создания одного отсортированного массива из двух таких же.
 * @author Alexey Makarov
 * @since 29.07.2018
 * @version 0.1
 */
public class Copy {

    private int[] copySort(BiPredicate<Integer, Integer> sort, int[] a, int[] b) {
        int indexa = 0;
        int indexb = 0;
        int[] result = new int[a.length + b.length];
        for (int i = 0; i < a.length + b.length; i++) {
            if ((indexb == b.length) || indexa != a.length && sort.test(a[indexa], b[indexb])) {
                result[i] = a[indexa];
                indexa++;
            } else {
                result[i] = b[indexb];
                indexb++;
            }
        }
        return result;
    }

    /**
     * Создаёт новый отсортированный массив из двух других отсортированных по возрастанию.
     * @param a первый массив
     * @param b второй массив
     * @return отсортированный массив включающий элементы массивов указанных в параметрах.
     */
    public int[] copySortAsc(int[] a, int[] b) {
        return copySort((c, d) -> c < d, a, b);
    }

    /**
     * Создаёт новый отсортированный массив из двух других отсортированных по убыванию.
     * @param a первый массив
     * @param b второй массив
     * @return отсортированный массив включающий элементы массивов указанных в параметрах.
     */
    public int[] copySortDesc(int[] a, int[] b) {
        return copySort((c, d) -> c > d, a, b);
    }



}
