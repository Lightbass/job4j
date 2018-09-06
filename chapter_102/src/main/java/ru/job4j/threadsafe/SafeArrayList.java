package ru.job4j.threadsafe;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.list.CustomArrayList;

import java.util.Iterator;

/**
 * Класс - потокобезопасное хранилище пользователей.
 * @author Alexey Makarov
 * @since 06.09.18
 * @version 0.1
 */
@ThreadSafe
public class SafeArrayList<E> {
    @GuardedBy("this")
    CustomArrayList<E> list;

    /**
     * Конструктор инициализирует массив данных, копируя ссылку на него из параметров.
     */
    public SafeArrayList(CustomArrayList<E> list) {
        this.list = list;
    }

    /**
     * Метод добавляет объект к списку.
     * @param model объект.
     */
    public synchronized void add(E model) {
        list.add(model);
    }

    /**
     * Метод возвращает объект из контейнера.
     * @param index индекс нужного объекта.
     * @return возвращаемый объект по данному индексу.
     */
    public synchronized E get(int index) {
        return list.get(index);
    }

    /**
     * Метод удаляет объект из контейнера.
     * @param index индекс удаляемого объекта.
     */
    public synchronized E delete(int index) {
        return list.delete(index);
    }

    /**
     * Метод возвращает кол-во элементов в контейнере.
     * @return кол-во элементов.
     */
    public synchronized int size() {
        return list.size();
    }

    /**
     * Метод возвращает итератор контейнера.
     * @return итератор.
     */
    public Iterator<E> iterator() {
        return copy().iterator();
    }

    private synchronized CustomArrayList<E> copy() {
        CustomArrayList<E> result = new CustomArrayList<>();
        for (E current : list) {
            result.add(current);
        }
        return result;
    }
}
