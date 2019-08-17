package ru.job4j.array;

/**
 * Класс для проверки строки на то, что слово заканчивается нужным постфиксом.
 * @author Alexey Makarov
 * @since 0.1
 * @version 0.1
 */
public class EndsWith {

    /**
     * Проверяет. что слово заканчивается постфиксом.
     * @param word слово.
     * @param post префикс.
     * @return {@code true}, если слово заканчивается постфиксом; {@code false}, если нет.
     */
    public boolean endsWith(String word, String post) {
        boolean result = true;
        if (word.length() >= post.length()) {
            char[] pst = post.toCharArray();
            char[] wrd = word.toCharArray();
            for (int i = 0; i < pst.length; i++) {
                if (wrd[wrd.length - 1 - i] != pst[pst.length - 1 - i]) {
                    result = false;
                }
            }
        }
        return result;
    }
}
