package ru.job4j.exam;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.function.BiFunction;

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
     * каждый из потоков, добавляемый этим методом сразу запускается и ждет, пока
     * его пробудит с помощью метода notify() предыдущий поток в списке, как только
     * он пробудится, он выведет число нужное кол-во раз и пробудит следующий в списке
     * поток и так по кругу.
     * @param num число, которое будет добавляться подряд в строку.
     */
    public void addNumThread(final int num) {
        final int index = listThread.size();
        listThread.add(new Thread(() -> {
            final Thread current = listThread.get(index);
            synchronized (current) {
                while (working) {
                    try {
                        current.wait();
                        for (int i = 0; i != numbersInRow; i++) {
                            sbuf.putToBuffer(num);
                        }
                        final int next = (listThread.size() - 1) == index ? 0 : (index + 1);
                        synchronized (listThread.get(next)) {
                            listThread.get(next).notify();
                        }
                    } catch (InterruptedException ie) {
                        working = false;
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }));
        listThread.get(index).start();
    }

    /**
     * Метод пробуждает первый поток, чтобы он начал прописывать в строку свои первые числа.
     * Перед тем, как вызвать для него notify() метод проверяет, что поток запустился и ждет
     * в нужном месте в состоянии WAITING, иначе метод бы вызвал notify(), когда поток ещё
     * даже не зашел в свой монитор.
     */
    public void startSwitcher() {
        while (listThread.get(0).getState() != Thread.State.WAITING) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
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
     * Метод выводит содержимое строки на данный момент.
     * @return содержимое строки.
     */
    @Override
    public String toString() {
        return sbuf.toString();
    }
}
