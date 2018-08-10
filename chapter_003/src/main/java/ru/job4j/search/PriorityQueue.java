package ru.job4j.search;

import java.util.LinkedList;

/**
 * Очередь с возможностью вытаскивать элементы с
 * наибольшим приоритетом.
 * @author Alexey Makarov
 * @since 10.08.18
 * @version 0.1
 */
public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Метод должен вставлять в нужную позицию элемент.
     * Позиция определять по полю приоритет.
     * Для вставик использовать add(int index, E value)
     * @param task задача
     */
    public void put(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getPriority() > task.getPriority()) {
                tasks.add(i, task);
                return;
            }
        }
        tasks.add(task);
    }

    /**
     * Взять следующую по приоритету задачу.
     * @return задача.
     */
    public Task take() {
        return this.tasks.poll();
    }
}
