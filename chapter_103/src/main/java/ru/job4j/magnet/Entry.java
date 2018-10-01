package ru.job4j.magnet;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Класс - поле.
 * @author Alexey Makarov
 * @since 01.10.2018
 * @version 0.1
 */
@XmlRootElement
public class Entry {
    private int field;

    /**
     * Конструктор дефолтный.
     */
    public Entry() {

    }

    /**
     * Конструктор инициализирует значение поля.
     * @param field поле.
     */
    public Entry(int field) {
        this.field = field;
    }

    /**
     * Метод возвращает значение поля.
     * @return значение.
     */
    public int getField() {
        return field;
    }

    /**
     * Метод меняет значение поля.
     * @param field новое значение.
     */
    public void setField(int field) {
        this.field = field;
    }
}
