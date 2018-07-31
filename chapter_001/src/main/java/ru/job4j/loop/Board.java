package ru.job4j.loop;

/**
 * Класс для рисования шахматной доски.
 * @author Alexey Makarov
 * @since 31.07.18
 * @version 0.1
 */
public class Board {

    /**
     * Метод для рисования доски в псевдографике.
     * @param width ширина поля.
     * @param height высота поля.
     * @return текстовое шахматное поле.
     */
    public String paint(int width, int height) {
        StringBuilder sb = new StringBuilder();
        String ln = System.lineSeparator();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if ((i + j) % 2 == 0) {
                    sb.append("X");
                } else {
                    sb.append(" ");
                }
            }
            sb.append(ln);
        }
        return sb.toString();
    }
}
