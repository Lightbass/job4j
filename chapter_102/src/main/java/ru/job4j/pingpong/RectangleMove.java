package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

/**
 * Класс для перемещения Rectangle в отдельном потоке.
 * @author Alexey Makarov
 * @since 03.09.18
 * @version 0.1
 */
public class RectangleMove implements Runnable {
    private final Rectangle rect;
    private int stepX = 1;
    private int stepY = 1;
    private int sceneWidth;
    private int sceneHeight;
    private boolean run = true;

    /**
     * Конструктор инициализирующий поля для квадрата, ширины рабочей области и высоты.
     * @param rect квадрат.
     * @param sceneWidth ширина экрана.
     * @param sceneHeight высота экрана.
     */
    public RectangleMove(Rectangle rect, int sceneWidth, int sceneHeight) {
        this.rect = rect;
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;
    }

    /**
     * Бесконечная обработка местоположения квадрата и перемещение его в нужное направление.
     */
    @Override
    public void run() {
        while (run) {
            moveFigure();
        }
    }

    /**
     * Обработка местоположения квадрата и перемещение его в нужное направление.
     */
    public void moveFigure() {
        this.rect.setX(this.rect.getX() + stepX);
        this.rect.setY(this.rect.getY() + stepY);
        if (this.rect.getX() >= sceneWidth || this.rect.getX() <= 0) {
            stepX *= -1;
        }
        if (this.rect.getY() >= sceneHeight || this.rect.getY() <= 0) {
            stepY *= -1;
        }
        try {
            Thread.currentThread().sleep(30);
        } catch (InterruptedException e) {
            run = false;
        }
    }
}