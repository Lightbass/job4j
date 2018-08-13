package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс - банк.
 * @author Alexey Makarov
 * @since 13.08.18
 * @version 0.1
 */
public class Bank {

    /**
     * Мап со всеми пользователями и привязанными к ним аккаунтами.
     */
    private HashMap<User, List<Account>> data = new HashMap<>();

    /**
     * Возвращает объект пользователя, проводя поиск по паспорту.
     * @param passport паспорт.
     * @return пользователь.
     */
    private User findUserByPassport(String passport) {
        User result = null;
        for (User user : data.keySet()) {
            if (user.getPassport().equals(passport)) {
                result = user;
                break;
            }
        }
        return result;
    }

    /**
     * Возвращает объект счета, проводя поиск по реквезитам в списке.
     * @param accs массив с аккаунтами.
     * @param req искомые реквизиты.
     * @return счет.
     */
    private Account findAccountByRequisites(List<Account> accs, String req) {
        Account result = null;
        for (Account acc : accs) {
            if (acc.getRequisites().equals(req)) {
                result = acc;
                break;
            }
        }
        return result;
    }

    /**
     * Добавить пользователя.
     * @param user пользователь.
     */
    public void addUser(User user) {
        data.putIfAbsent(user, new LinkedList<Account>());
    }

    /**
     * Удалить пользователя.
     * @param user пользователь, которого нужно удалить.
     */
    public boolean deleteUser(User user) {
        boolean result = true;
        if (data.remove(user) == null) {
            result = false;
        }
        return result;
    }

    /**
     * Создать и добавить счет к учетной записи пользователя.
     * @param passport реквизиты счета.
     * @param account пользователь к которому добавляется счет.
     */
    public boolean addAccountToUser(String passport, Account account) {
        boolean result = false;
        User user = findUserByPassport(passport);
        if (user != null) {
            data.get(user).add(account);
            result = true;
        }
        return result;
    }

    /**
     * Удалить счет из учетной записи.
     * @param passport паспорт.
     * @param account счет.
     */
    public boolean deleteAccountFromUser(String passport, Account account) {
        boolean result = false;
        User user = findUserByPassport(passport);
        if (user != null) {
            result = data.get(user).remove(account);
        }
        return result;
    }

    /**
     * Метод возвращает список со счетами пользователя.
     * @param passport паспорт.
     * @return счета.
     */
    public List<Account> getUserAccounts(String passport) {
        return data.get(findUserByPassport(passport));
    }

    /**
     * Метод переводит денежные средства с одного счета на другой.
     * @param srcPassport паспорт от кого поступают деньги.
     * @param srcRequisite реквизиты откуда поступают деньги.
     * @param destPassport паспорт кому поступают деньги.
     * @param dstRequisite реквизиты куда поступают деньги.
     * @param amount кол-во отправляемых денежных средств.
     * @return успешна ли операция.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount) {
        boolean result = false;
        Account accSrc = findAccountByRequisites(data.get(findUserByPassport(srcPassport)), srcRequisite);
        Account accDest = findAccountByRequisites(data.get(findUserByPassport(destPassport)), dstRequisite);
        if (accSrc != null && accDest != null) {
            result = accSrc.transferTo(accDest, amount);
        }
        return result;
    }
}
