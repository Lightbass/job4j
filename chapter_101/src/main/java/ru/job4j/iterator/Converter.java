package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс для получения итератора итераторов.
 * @author Alexey Makarov
 * @since 16.08.18
 * @version 0.1
 */
public class Converter {

    /**
     * Метод возвращает итератор, который перечисляет итераторы внутри итератора.
     * @param it итератор с вложенным итератором.
     * @return итератор перечисления всех итераторов.
     */
    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<Integer>() {
            Iterator<Integer> current;

            {
                if (it.hasNext()) {
                    current = it.next();
                }
            }

            /**
             * Метод проверяет, есть ли следующее число в итераторе
             * @return есть ли следующий элемент.
             */
            @Override
            public boolean hasNext() {
                boolean result = true;
                while (!current.hasNext()) {
                    if (it.hasNext()) {
                        current = it.next();
                    } else {
                        result = false;
                        break;
                    }
                }
                return result;
            }

            /**
             * Метод возвращает число одного из итераторов.
             * @return число.
             */
            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return current.next();
            }
        };
    }
}
