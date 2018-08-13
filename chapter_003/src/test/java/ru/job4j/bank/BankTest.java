package ru.job4j.bank;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса ConvertList2ArrayTest.
 * @author Alexey Makarov
 * @since 10.08.18
 * @version 0.1
 */
public class BankTest {

    @Test
    public void whenAddUserAndDeleteThenTrue() {
        Bank bank = new Bank();
        User a = new User("Alexey", "1111-111111");
        bank.addUser(a);
        assertThat(bank.getUserAccounts("1111-111111").isEmpty(), is(true));
        bank.deleteUser(a);
        assertThat(bank.getUserAccounts("1111-111111") == null, is(true));
    }

    @Test
    public void whenAddUserAndAccountAndGetThenTrue() {
        Bank bank = new Bank();
        User a = new User("Alexey", "1111-111111");
        Account a1 = new Account("123123123", 30);
        bank.addUser(a);
        bank.addAccountToUser("1111-111111", a1);
        assertThat(bank.getUserAccounts("1111-111111").get(0), is(a1));
    }

    @Test
    public void whenTransactionThenOK() {
        Bank bank = new Bank();
        User a = new User("Alexey", "1111-111111");
        Account a1 = new Account("123123123", 300);
        Account a2 = new Account("321321321", 200);
        User b = new User("Ivan", "2222-222222");
        Account b1 = new Account("456456456", 130);
        Account b2 = new Account("654654654", 150);
        bank.addUser(a);
        bank.addAccountToUser("1111-111111", a1);
        bank.addAccountToUser("1111-111111", a2);
        bank.addUser(b);
        bank.addAccountToUser("2222-222222", b1);
        bank.addAccountToUser("2222-222222", b2);
        bank.transferMoney("1111-111111", "123123123", "2222-222222",
                "654654654", 150);
        assertThat(b2.getValue(), is(300.0));
    }

    @Test
    public void whenCreate2AccountsAndDeleteOneThen1Left() {
        Bank bank = new Bank();
        User a = new User("Alexey", "1111-111111");
        Account a1 = new Account("123123123", 300);
        Account a2 = new Account("321321321", 200);
        bank.addUser(a);
        bank.addAccountToUser("1111-111111", a1);
        bank.addAccountToUser("1111-111111", a2);
        bank.deleteAccountFromUser("1111-111111", a2);
        assertThat(bank.getUserAccounts("1111-111111").get(0), is(a1));
    }
}
