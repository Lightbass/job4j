package ru.job4j.exam;

/**
 * Класс - чудовище, управляемое отдельным потоком.
 * @author Alexey Makarov
 * @since 10.09.18
 * @version 0.1
 */
public class Enemy extends Thread {
    private Hero enemy;
    private int x;
    private int y;
    private Board board;
    private int moveX = 1;
    private int moveY = 0;

    /**
     * Конструктор инициализирует поля игрового поля, координат x и y, где изначально стоит герой.
     * @param x координата х.
     * @param y координата y.
     */
    public Enemy(Board board, int x, int y) {
        this.board = board;
        this.x = x;
        this.y = y;
        start();
    }

    /**
     * Метод - задача нити чудовища.
     */
    @Override
    public void run() {
        enemy = new Hero(board, x, y);
        try {
            while (true) {
                Thread.sleep(1000);
                if (!enemy.move(moveX, moveY, 4000)) {
                    changeDirection();
                }
            }
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    /**
     * Метод изменяет направление.
     */
    public void changeDirection() {
        if (moveX == 1) {
            moveX = 0;
            moveY = 1;
        } else if (moveY == 1) {
            moveY = 0;
            moveX = -1;
        } else if (moveX == -1) {
            moveY = -1;
            moveX = 0;
        } else {
            moveX = 1;
            moveY = 0;
        }
    }
}
