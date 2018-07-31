package ru.job4j.loop;

/**
 * Класс для рисования пирамиды.
 * @author Alexey Makarov
 * @since 31.07.18
 * @version 0.1
 */
public class Paint {

    /**
     * Метод рисует пирамиду в правой стороне из символа ^ и пробелов.
     * @param height высота пирамиды.
     * @return текстовый вывод пирамиды.
     */
    public String rightTrl(int height) {
        StringBuilder screen = new StringBuilder();
        int weight = height;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (row >= column) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

    /**
     * Метод рисует пирамиду в левой стороне из символа ^ и пробелов.
     * @param height высота пирамиды.
     * @return текстовый вывод пирамиды.
     */
    public String leftTrl(int height) {
        StringBuilder screen = new StringBuilder();
        int weight = height;
        for (int row = 0; row != height; row++) {
            for (int column = 0; column != weight; column++) {
                if (row >= weight - column - 1) {
                    screen.append("^");
                } else {
                    screen.append(" ");
                }
            }
            screen.append(System.lineSeparator());
        }
        return screen.toString();
    }

    /**
     * Метод рисует пирамиду из символа ^ и пробелов.
     * @param height высота пирамиды.
     * @return текстовый вывод пирамиды.
     */
    public String pyramid(int height) {
        StringBuilder sb = new StringBuilder();
        String ln = System.lineSeparator();
        for (int n = 0; n < height; n++) {
            for (int m = 0; m != height - n - 1; m++) {
                sb.append(' ');
            }
            for (int m = 0; m != n * 2 + 1; m++) {
                sb.append("^");
            }
            for (int m = 0; m != height - n - 1; m++) {
                sb.append(' ');
            }
            sb.append(ln);
        }
        return sb.toString();
    }
}
