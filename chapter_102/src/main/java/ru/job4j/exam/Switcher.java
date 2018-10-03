package ru.job4j.exam;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * Класс - свитчер потоков. Я сделал не для двух потоков, а для нескольких, тут можно
 * добавить сколько угодно потоков и они будут работать последовательно. Это первое решение,
 * которое пришло мне в голову.
 * @author Alexey Makarov
 * @since 18.09.18
 * @version 0.1
 */
public class Switcher {

    /** Список потоков */
    private final ArrayList<Thread> listThread = new ArrayList<>();
    /** Заполняемая строка */
    private final StringBuffer sbuf = new StringBuffer();
    /** Количество цифр подряд */
    private final int numbersInRow;
    /** Статус работы свитчера */
    private volatile boolean working = true;
    /** Счетчик для запуска всех потоков одновременно с нужной точки */
    private volatile CountDownLatch latch;

    /**
     * Конструктор инициализирует кол-во цифр, которые подряд добавятся в строку.
     * @param numbersInRow кол-во цифр.
     */
    public Switcher(int numbersInRow) {
        this.numbersInRow = numbersInRow;
    }

    /**
     * Метод создает поток, который будет писать подряд одну и ту же цифру какое-то
     * кол-во раз и добавляет объект потока в список. Метод сделан так, что каждый
     * поток для ожидания использует в качестве монитора свой же объект потока и
     * каждый из потоков, добавляемый этим методом при запуске будет ждать, пока
     * CountDownLatch не обнулится. После преодоления метода счетчика await() поток
     * ждет, пока его пробудит с помощью метода notify() предыдущий поток в списке,
     * как только с помощью метода notify() предыдущий поток в списке, как только
     * он пробудится, он выведет число нужное кол-во раз и пробудит следующий в
     * списке поток и так по кругу.
     * @param num число, которое будет добавляться подряд в строку.
     */

    public void addNumThread(final int num) {
        final int current = listThread.size();
        listThread.add(new Thread(() -> {
            final int next = (listThread.size() - 1) == current ? 0 : (current + 1);
            final Thread currentMonitor = listThread.get(current);
            final Thread nextMonitor = listThread.get(next);
            synchronized (currentMonitor) {
                waitForCount();
                while (working) {
                    try {
                        currentMonitor.wait();
                        for (int i = 0; i != numbersInRow; i++) {
                            sbuf.putToBuffer(num);
                        }
                        synchronized (nextMonitor) {
                            nextMonitor.notify();
                        }
                    } catch (InterruptedException ie) {
                        interruptAllThreads();
                    }
                }
            }
        }));
    }

    /**
     * Метод запускает все потоки, которые будут ждать до момента, пока счетчик {@code latch}
     * не обнулится, как только это произойдет и первый поток освободит монитор, дойдя до
     * строки wait(), его тут же пробудит данный метод и цепочка исполнения потоков начнет
     * свою работу.
     */
    public void startSwitcher() {
        latch = new CountDownLatch(listThread.size());
        listThread.stream().forEach(t -> t.start());
        try {
            latch.await();
        } catch (InterruptedException ie) {
            interruptAllThreads();
        }
        synchronized (listThread.get(0)) {
            listThread.get(0).notify();
        }
    }

    /**
     * Метод завершает работу свитчера, прерывая один поток из-за чего
     * выставляется флаг и завершают работу все потоки.
     */
    public void terminateSwitcher() {
        listThread.get(0).interrupt();
    }

    /**
     * Метод приостанавливает поток, пока не обнулится CountDownLatch.
     */
    private void waitForCount() {
        try {
            latch.countDown();
            latch.await();
        } catch (InterruptedException ie) {
            interruptAllThreads();
        }
    }
    /**
     * Метод меняет флаг работы на {@code false} и прерывает текущий поток.
     */
    private void interruptAllThreads() {
        working = false;
        Thread.currentThread().interrupt();
    }

    /**
     * Метод выводит содержимое строки на данный момент.
     * @return содержимое строки.
     */
    @Override
    public String toString() {
        return sbuf.toString();
    }
}
