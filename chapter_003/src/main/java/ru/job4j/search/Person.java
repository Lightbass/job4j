package ru.job4j.search;

/**
 * Класс персона, включающий в себя информацию об имени, фамилии, телефоне и адресе.
 * @author Alexey Makarov
 * @since 10.08.18
 * @version 0.1
 */
public class Person {
    private String name;
    private String surname;
    private String phone;
    private String address;

    /**
     * Конструктор инициализирующий поля в контейнере.
     * @param name имя.
     * @param surname фамилия.
     * @param phone телефон.
     * @param address адрес.
     */
    public Person(String name, String surname, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    /**
     * Метод возвращает имя.
     * @return имя
     */
    public String getName() {
        return name;
    }

    /**
     * Метод возвращает фамилию.
     * @return фамилия
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Метод возвращает телефон.
     * @return телефон
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Метод возвращает адрес.
     * @return адрес
     */
    public String getAddress() {
        return address;
    }
}