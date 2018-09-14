package ru.job4j.exam;

/**
 * Класс - буферная строка, которую можно дополнять цифрами.
 * @author Alexey Makarov
 * @since 14.09.18
 * @version 0.1
 */
public class StringBuffer {
    private StringBuilder sb = new StringBuilder();

    /**
     * Метод добавляет число в конец строки.
     * @param num число.
     */
    public void putToBuffer(int num) {
        sb.append(num);
    }

    /**
     * Метод выводит всю строку с добавленными числами.
     * @return строка с числами.
     */
    @Override
    public String toString() {
        return sb.toString();
    }
}
