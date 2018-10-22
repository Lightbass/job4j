package ru.job4j.crud;

import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * JUnit тест класса UserServlet.
 * @author Alexey Makarov
 * @since 22.10.18
 * @version 0.1
 */
public class UserServletTest {
    @Test
    public void whenAddUserThenOk() throws ServletException, IOException {
        UserServlet userServlet = new UserServlet();
        userServlet.init();
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("action")).thenReturn("add");
        when(request.getParameter("name")).thenReturn("Alexey");
        when(request.getParameter("login")).thenReturn("Bass");
        when(request.getParameter("password")).thenReturn("alexey");
        when(request.getParameter("email")).thenReturn("alex@gmail.com");
        try {
            userServlet.doPost(request, response);
        } catch (NullPointerException npe) {
            npe.fillInStackTrace();
        }
        Collection<User> users = MemoryStore.getInstance().findAll();
        System.out.println(users.size());
        assertThat(users.iterator().next().getLogin(), is("Bass"));
    }
}
