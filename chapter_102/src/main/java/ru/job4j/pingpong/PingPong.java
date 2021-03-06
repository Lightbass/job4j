package ru.job4j.pingpong;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Класс для отображения UI пинг понга.
 * @author Alexey Makarov
 * @since 27.08.18
 * @version 0.1
 */
public class PingPong extends Application {
    private static final String JOB4J = "Пинг-понг www.job4j.ru";

    /**
     * Метод отрисовывает окно.
     * @param stage окно.
     */
    @Override
    public void start(Stage stage) {
        int limitX = 300;
        int limitY = 300;
        Group group = new Group();
        Rectangle rect = new Rectangle(50, 100, 10, 10);
        group.getChildren().add(rect);
        Thread thread = new Thread(new RectangleMove(rect, limitX, limitY));
        thread.start();
        stage.setScene(new Scene(group, limitX, limitY));
        stage.setOnCloseRequest(
                event -> thread.interrupt()
        );
        stage.setTitle(JOB4J);
        stage.setResizable(false);
        stage.show();
    }
}