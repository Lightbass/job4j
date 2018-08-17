package ru.job4j.store;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * JUnit тест класса RoleStore.
 * @author Alexey Makarov
 * @since 17.08.18
 * @version 0.1
 */
public class RoleStoreTest {
    RoleStore<Role> store;
    Role u1, u2, u3, u4, u5;
    @Before
    public void prepare() {
        store = new RoleStore(10);
        u1 = new Role("ID 1");
        u2 = new Role("ID 2");
        u3 = new Role("ID 3");
        u4 = new Role("ID 4");
        u5 = new Role("ID 5");
        store.add(u1);
        store.add(u2);
        store.add(u3);
        store.add(u4);
        store.add(u5);
    }

    @Test
    public void whenAdd5UsersAndGetItById() {
        assertThat(store.findById("ID 1"), is(u1));
        assertThat(store.findById("ID 2"), is(u2));
        assertThat(store.findById("ID 3"), is(u3));
        assertThat(store.findById("ID 4"), is(u4));
        assertThat(store.findById("ID 5"), is(u5));
    }

    @Test
    public void whenReplaceUserAndGetItById() {
        Role u6 = new Role("ID 6");
        store.replace("ID 1", u6);
        assertThat(store.findById("ID 6"), is(u6));
        assertThat(store.findById("ID 1"), nullValue());
    }

    @Test
    public void whenDeleteUserAndGetItById() {
        store.delete("ID 1");
        assertThat(store.findById("ID 1"), nullValue());
    }
}
