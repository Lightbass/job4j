package ru.job4j.array;

/**
 * Класс для проверки строки на то, что она начинаются с нужного префикса.
 * @author Alexey Makarov
 * @since 01.08.18
 * @version 0.1
 */
public class ArrayChar {
    private char[] data;

    /**
     * Конструктор сохраняет строку, в которой будет искаться префикс.
     * @param line входная строка.
     */
    public ArrayChar(String line) {
        this.data = line.toCharArray();
    }

    /**
     * Проверяет. что слово начинается с префикса.
     * @param prefix префикс.
     * @return если слово начинаеться с префикса
     */
    public boolean startWith(String prefix) {
        boolean result = true;
        char[] value = prefix.toCharArray();
        if (value.length > data.length) {
            result = false;
            return result;
        }
        for (int i = 0; i < value.length; i++) {
            if (value[i] != data[i]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
