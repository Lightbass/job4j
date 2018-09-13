package ru.job4j.storage;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса UserStore.
 * @author Alexey Makarov
 * @since 05.09.18
 * @version 0.1
 */
public class UserStoreTest {

    /**
     * Класс создает поток для добавления пользователей в хранилище.
     */
    private class ThreadAdd extends Thread {
        private final UserStore store;

        private ThreadAdd(final UserStore store) {
            this.store = store;
        }

        @Override
        public void run() {
            for (int i = 0; i != 20; i++) {
                store.add(new User(i, 100));
            }
        }
    }

    /**
     * Класс создает поток для перевода средств между пользователями.
     */
    private class ThreadTransfer extends Thread {
        private final UserStore store;
        private final int fromId;
        private final int toId;
        private final int amount;

        private ThreadTransfer(final UserStore store, int fromId, int toId, int amount) {
            this.fromId = fromId;
            this.toId = toId;
            this.amount = amount;
            this.store = store;
        }

        @Override
        public void run() {
            store.transfer(fromId, toId, amount);
        }
    }

    @Test
    public void whenExecute3ThreadThen3() throws InterruptedException {
        final UserStore store = new UserStore();
        Thread add = new ThreadAdd(store);
        Thread transfer1 = new ThreadTransfer(store, 0, 1, 15);
        Thread transfer2 = new ThreadTransfer(store, 1, 0, 45);
        Thread transfer3 = new ThreadTransfer(store, 0, 1, 15);
        add.start();
        add.join();
        transfer1.start();
        transfer2.start();
        transfer3.start();
        transfer1.join();
        transfer2.join();
        transfer3.join();
        assertThat(store.get(0).getCash(), is(115));
        assertThat(store.get(1).getCash(), is(85));
    }
}
