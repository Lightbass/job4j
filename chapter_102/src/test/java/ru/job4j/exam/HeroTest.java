package ru.job4j.exam;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;


/**
 * JUnit тест класса Bomberman.
 * @author Alexey Makarov
 * @since 10.09.18
 * @version 0.1
 */
public class HeroTest {

    /**
     * Метод показывает как примерно нужно использовать класс, поскольку задача основана на блокировках, важно, чтобы
     * главный герой управлялся не с того же потока, который создаёт поле( в том случае,
     * если флаг blocked = {@code true}.
     * @throws InterruptedException если поток будет неожиданно прерван.
     */
    public void sampleOfExecuteGame() throws InterruptedException {
        Board board = new Board(10, true);
        Enemy enemy1 = new Enemy(board, 4, 5);
        Enemy enemy2 = new Enemy(board, 4, 7);
        Enemy enemy3 = new Enemy(board, 4, 9);
        final Hero hero = new Hero(board, 0, 0);
        Thread thread = new Thread(() -> {
            try {
                hero.move(1, 0, 100);
                Thread.sleep(1000);
                hero.move(1, 0, 100);
                Thread.sleep(1000);
                hero.move(0, 1, 100);
                hero.move(0, 1, 100);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        });
        thread.start();
        enemy1.join();
        enemy2.join();
        enemy3.join();
    }

    /**
     * Тест, когда герой пытается зайти на заблокированную клетку.
     */
    @Test
    public void whenHeroMovesOnBlockedAreaThenNothing() {
        Board board = new Board(10, true);
        Hero hero = new Hero(board, 0, 1);
        Thread thread = new Thread(() -> {
            hero.move(1, 0, 100);
        });
        thread.start();
        assertThat(hero.getCurrentCell().getX() == 0
                && hero.getCurrentCell().getY() == 1, is(true));
    }

    /**
     * Тест, когда герой пытается зайти на заблокированную клетку.
     */
    @Test
    public void whenHeroMovesOnBoundsThenNothing() {
        Board board = new Board(10, false);
        Hero hero = new Hero(board, 0, 1);
        Thread thread = new Thread(() -> {
            hero.move(-1, 0, 100);
        });
        thread.start();
        assertThat(hero.getCurrentCell().getX() == 0
                && hero.getCurrentCell().getY() == 1, is(true));
    }
}
