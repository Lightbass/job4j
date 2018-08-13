package ru.job4j.bank;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса Account.
 * @author Alexey Makarov
 * @since 10.08.18
 * @version 0.1
 */
public class AccountTest {

    @Test
    public void whenTransaction30Then130() {
        Account a1 = new Account("123123123", 30);
        Account a2 = new Account("321321321", 100);
        a1.transferTo(a2, 30);
        assertThat(a2.getValue(), is(130.0));
    }
}
