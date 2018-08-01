package ru.job4j.tictactoe;

/**
 * Created by a.makarov on 01.08.2018.
 */
public class Logic3T {
    private final Figure3T[][] table;

    public Logic3T(Figure3T[][] table) {
        this.table = table;
    }

    public boolean isWinnerX() {
        boolean result = false;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (!table[i][j].hasMarkX()) {
                    break;
                }
                if (j == table.length - 1) {
                    result = true;
                    i = table.length;
                    break;
                }
            }
            for (int j = 0; j < table.length && i != table.length; j++) {
                if (!table[j][i].hasMarkX()) {
                    break;
                }
                if (j == table.length - 1) {
                    result = true;
                    i = table.length;
                    break;
                }
            }
            if (i == 0) {
                for (int j = 0; j < table.length; j++) {
                    if (!table[j][j].hasMarkX()) {
                        break;
                    }
                    if (j == table.length - 1) {
                        result = true;
                        i = table.length;
                        break;
                    }
                }
            } else if (i == 1) {
                for (int j = 0; j < table.length; j++) {
                    if (!table[j][table.length - j - 1].hasMarkX()) {
                        break;
                    }
                    if (j == table.length - 1) {
                        result = true;
                        i = table.length;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public boolean isWinnerO() {
        boolean result = false;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table.length; j++) {
                if (!table[i][j].hasMarkO()) {
                    break;
                }
                if (j == table.length - 1) {
                    result = true;
                    i = table.length;
                    break;
                }
            }
            for (int j = 0; j < table.length && i != table.length; j++) {
                if (!table[j][i].hasMarkO()) {
                    break;
                }
                if (j == table.length - 1) {
                    result = true;
                    i = table.length;
                    break;
                }
            }
            if (i == 0) {
                for (int j = 0; j < table.length; j++) {
                    if (!table[j][j].hasMarkO()) {
                        break;
                    }
                    if (j == table.length - 1) {
                        result = true;
                        i = table.length;
                        break;
                    }
                }
            } else if (i == 1) {
                for (int j = 0; j < table.length; j++) {
                    if (!table[j][table.length - j - 1].hasMarkO()) {
                        break;
                    }
                    if (j == table.length - 1) {
                        result = true;
                        i = table.length;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public boolean hasGap() {
        return true;
    }
}