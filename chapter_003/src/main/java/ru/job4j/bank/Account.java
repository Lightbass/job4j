package ru.job4j.bank;

/**
 * Класс - аккаунт с информацией о средствах и реквизитах.
 * @author Alexey Makarov
 * @since 13.08.18
 * @version 0.1
 */
public class Account {
    private double value;
    private String requisites;

    /**
     * Конструктор инициализирует кол-во денежных средств на счете и реквизиты счета.
     * @param requisites реквизиты.
     * @param value денежные средства.
     */
    public Account(String requisites, long value) {
        this.requisites = requisites;
        this.value = value;
    }

    /**
     * Метод возвращает кол-во денежных средств.
     * @return денежные средства
     */
    public double getValue() {
        return value;
    }

    /**
     * Метод возвращает кол-во денежных средств.
     * @param value новое кол-во денежных средств.
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Метод возвращает реквизиты.
     * @return реквизиты.
     */
    public String getRequisites() {
        return requisites;
    }

    /**
     * Перевод средств с данного счета на счет в параметрах.
     * @param dest счет на который переводятся деньги.
     * @param transaction денежные средства.
     * @return
     */
    public boolean transferTo(Account dest, double transaction) {
        boolean result = false;
        if (value >= transaction) {
            dest.setValue(dest.getValue() + transaction);
            setValue(value - transaction);
            result = true;
        }
        return result;
    }
}
