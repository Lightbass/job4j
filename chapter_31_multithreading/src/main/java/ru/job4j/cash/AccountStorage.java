package ru.job4j.cash;

import java.util.HashMap;
import java.util.Optional;

public class AccountStorage {
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        boolean contains = false;
        if (!accounts.containsKey(account.id())) {
            accounts.put(account.id(), account);
            contains = true;
        }
        return contains;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id(), account) != null;
    }

    public synchronized boolean delete(int id) {
        return accounts.remove(id) != null;
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        if (amount > 0) {
            Account accountFrom = accounts.get(fromId);
            Account accountTo = accounts.get(toId);
            if (accountFrom != null && accountTo != null && accountFrom.amount() >= amount) {
                accountFrom = new Account(accountFrom.id(), accountFrom.amount() - amount);
                accountTo = new Account(accountTo.id(), accountTo.amount() + amount);
                accounts.replace(accountFrom.id(), accountFrom);
                accounts.replace(accountTo.id(), accountTo);
                result = true;
            }
        }
        return result;
    }
}
