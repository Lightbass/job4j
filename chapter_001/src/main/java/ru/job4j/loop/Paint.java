package ru.job4j.loop;

/**
 * Класс для рисования пирамиды.
 * @author Alexey Makarov
 * @since 31.07.18
 * @version 0.1
 */
public class Paint {
    /**
     * Метод рисует пирамиду из символа ^ и пробелов.
     * @param h высота пирамиды.
     * @return текстовый вывод пирамиды.
     */
    public String pyramid(int h) {
        StringBuilder sb = new StringBuilder();
        String ln = System.lineSeparator();
        for (int n = 0; n < h; n++) {
            for (int m = 0; m < h - n - 1; m++) {
                sb.append(' ');
            }
            for (int m = 0; m < n * 2 + 1; m++) {
                sb.append("^");
            }
            for (int m = 0; m < h - n - 1; m++) {
                sb.append(' ');
            }
            sb.append(ln);
        }
        return sb.toString();
    }
}
