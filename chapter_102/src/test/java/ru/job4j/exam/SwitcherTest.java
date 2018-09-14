package ru.job4j.exam;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.stringContainsInOrder;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса Switcher.
 * @author Alexey Makarov
 * @since 18.09.18
 * @version 0.1
 */
public class SwitcherTest {

    /**
     * Тест для свитчера, который работает бесконечно.
     * Мы добавляем 3 потока со своими числами, которые они пишут в строку 10
     * раз подряд. Стартуем свитчер и ждем 300 мс, чтобы он проработал какое-то
     * время, останавливаем свитчер и сравниваем результа и видим, что он правильно
     * пишет числа подряд по кругу.
     * @throws InterruptedException если что-то прервало главный поток на {@code Thread.sleep()}.
     */
    @Test
    public void whenAdd3ThreadsThenOK() throws InterruptedException {
        Switcher switcher = new Switcher(10);
        switcher.addNumThread(10);
        switcher.addNumThread(20);
        switcher.addNumThread(30);
        switcher.startSwitcher();
        Thread.sleep(300);
        switcher.terminateSwitcher();
        assertThat(switcher.toString().substring(0, 60).equals("10101010101010101010"
                + "20202020202020202020" + "30303030303030303030"), is(true));
        assertThat(switcher.toString().substring(60, 120).equals("10101010101010101010"
                + "20202020202020202020" + "30303030303030303030"), is(true));
    }
}
