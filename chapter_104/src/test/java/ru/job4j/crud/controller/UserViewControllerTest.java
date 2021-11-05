package ru.job4j.crud.controller;

import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ru.job4j.crud.service.Validate;
import ru.job4j.crud.service.ValidateService;
import ru.job4j.crud.service.ValidateStub;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * JUnit тест класса UserViewController.
 * @author Alexey Makarov
 * @since 22.10.18
 * @version 0.1
 */
public class UserViewControllerTest {

    HttpSession session = new HttpSession() {

        private HashMap<String, Object> attr = new HashMap<>();

        @Override
        public long getCreationTime() {
            return 0;
        }

        @Override
        public String getId() {
            return null;
        }

        @Override
        public long getLastAccessedTime() {
            return 0;
        }

        @Override
        public ServletContext getServletContext() {
            return null;
        }

        @Override
        public void setMaxInactiveInterval(int i) {

        }

        @Override
        public int getMaxInactiveInterval() {
            return 0;
        }

        @Override
        public HttpSessionContext getSessionContext() {
            return null;
        }

        @Override
        public Object getAttribute(String s) {
            return attr.get(s);
        }

        @Override
        public Object getValue(String s) {
            return null;
        }

        @Override
        public Enumeration<String> getAttributeNames() {
            return null;
        }

        @Override
        public String[] getValueNames() {
            return new String[0];
        }

        @Override
        public void setAttribute(String s, Object o) {
            attr.put(s, o);
        }

        @Override
        public void putValue(String s, Object o) {

        }

        @Override
        public void removeAttribute(String s) {

        }

        @Override
        public void removeValue(String s) {

        }

        @Override
        public void invalidate() {

        }

        @Override
        public boolean isNew() {
            return false;
        }
    };

    @Test
    public void whenAddOneMoreUserThenFindIt() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        try (MockedStatic<ValidateService> validateService = Mockito.mockStatic(ValidateService.class)) {
            validateService.when(ValidateService::getInstance).thenReturn(validate);

            HttpServletRequest req = mock(HttpServletRequest.class);
            HttpServletResponse resp = mock(HttpServletResponse.class);
            UserViewController userViewController = new UserViewController();
            userViewController.init();
            when(req.getParameter("action")).thenReturn("add");
            when(req.getParameter("name")).thenReturn("Alexey");
            when(req.getParameter("login")).thenReturn("Alex");
            when(req.getParameter("password")).thenReturn("alexey");
            when(req.getParameter("email")).thenReturn("alex@gmail.com");
            when(req.getParameter("role")).thenReturn("true");
            try {
                userViewController.doPost(req, resp);
            } catch (NullPointerException npe) {
                npe.fillInStackTrace();
            }
            assertThat(validate.findAll().stream().anyMatch(u -> u.getName().equals("Alexey")), is(true));
        }
    }

    @Test
    public void whenDeleteUserWithoutAdminRoleThenNoChanges() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        try (MockedStatic<ValidateService> validateService = Mockito.mockStatic(ValidateService.class)) {
            validateService.when(ValidateService::getInstance).thenReturn(validate);
            HttpServletRequest req = mock(HttpServletRequest.class);
            HttpServletResponse resp = mock(HttpServletResponse.class);
            Mockito.when(req.getSession()).thenReturn(session);
            UserViewController userViewController = new UserViewController();
            userViewController.init();
            session.setAttribute("login", "mas");
            when(req.getParameter("action")).thenReturn("delete");
            when(req.getParameter("id")).thenReturn("0");
            try {
                userViewController.doPost(req, resp);
            } catch (NullPointerException npe) {
                npe.fillInStackTrace();
            }
            assertThat(validate.findAll().size(), is(2));
        }
    }

    @Test
    public void whenDeleteUserWithAdminRoleThenOneUserDeleted() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        try (MockedStatic<ValidateService> validateService = Mockito.mockStatic(ValidateService.class)) {
            validateService.when(ValidateService::getInstance).thenReturn(validate);
            HttpServletRequest req = mock(HttpServletRequest.class);
            HttpServletResponse resp = mock(HttpServletResponse.class);
            Mockito.when(req.getSession()).thenReturn(session);
            UserViewController userViewController = new UserViewController();
            userViewController.init();
            session.setAttribute("login", "vas");
            when(req.getParameter("action")).thenReturn("delete");
            when(req.getParameter("id")).thenReturn("0");
            try {
                userViewController.doPost(req, resp);
            } catch (NullPointerException npe) {
                npe.fillInStackTrace();
            }
            assertThat(validate.findAll().size(), is(1));
        }
    }

    @Test
    public void whenChangeUserWithoutAdminRoleThenNothingChanged() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        try (MockedStatic<ValidateService> validateService = Mockito.mockStatic(ValidateService.class)) {
            validateService.when(ValidateService::getInstance).thenReturn(validate);
            HttpServletRequest req = mock(HttpServletRequest.class);
            HttpServletResponse resp = mock(HttpServletResponse.class);
            Mockito.when(req.getSession()).thenReturn(session);
            UserViewController userViewController = new UserViewController();
            userViewController.init();
            session.setAttribute("login", "mas");
            when(req.getParameter("action")).thenReturn("update");
            when(req.getParameter("id")).thenReturn("0");
            when(req.getParameter("login")).thenReturn("allo");
            when(req.getParameter("password")).thenReturn("pass");
            when(req.getParameter("name")).thenReturn("allo");
            when(req.getParameter("email")).thenReturn("allo@allo.com");
            when(req.getParameter("role")).thenReturn("true");
            try {
                userViewController.doPost(req, resp);
            } catch (NullPointerException npe) {
                npe.fillInStackTrace();
            }
            assertThat(validate.findAll().stream().anyMatch(u -> u.getName().equals("Vasya")), is(true));
        }
    }

    @Test
    public void whenChangeUserSelfWithoutAdminRoleThenUserChanged() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        try (MockedStatic<ValidateService> validateService = Mockito.mockStatic(ValidateService.class)) {
            validateService.when(ValidateService::getInstance).thenReturn(validate);
            HttpServletRequest req = mock(HttpServletRequest.class);
            HttpServletResponse resp = mock(HttpServletResponse.class);
            Mockito.when(req.getSession()).thenReturn(session);
            UserViewController userViewController = new UserViewController();
            userViewController.init();
            session.setAttribute("login", "mas");
            when(req.getParameter("action")).thenReturn("update");
            when(req.getParameter("id")).thenReturn("1");
            when(req.getParameter("login")).thenReturn("mas");
            when(req.getParameter("password")).thenReturn("pass");
            when(req.getParameter("name")).thenReturn("allo");
            when(req.getParameter("email")).thenReturn("allo@allo.com");
            when(req.getParameter("role")).thenReturn("false");
            try {
                userViewController.doPost(req, resp);
            } catch (NullPointerException npe) {
                npe.fillInStackTrace();
            }
            validate.findAll().stream().map(v -> v.getName()).forEach(System.out::println);
            assertThat(validate.findAll().stream().anyMatch(u -> u.getName().equals("allo")), is(true));
        }
    }

    @Test
    public void whenChangeUserWithAdminRoleThenOneUserChanged() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        try (MockedStatic<ValidateService> validateService = Mockito.mockStatic(ValidateService.class)) {
            validateService.when(ValidateService::getInstance).thenReturn(validate);
            HttpServletRequest req = mock(HttpServletRequest.class);
            HttpServletResponse resp = mock(HttpServletResponse.class);
            Mockito.when(req.getSession()).thenReturn(session);
            UserViewController userViewController = new UserViewController();
            userViewController.init();
            session.setAttribute("login", "vas");
            when(req.getParameter("action")).thenReturn("update");
            when(req.getParameter("id")).thenReturn("0");
            when(req.getParameter("login")).thenReturn("allo");
            when(req.getParameter("password")).thenReturn("pass");
            when(req.getParameter("name")).thenReturn("allo");
            when(req.getParameter("email")).thenReturn("allo@allo.com");
            when(req.getParameter("role")).thenReturn("true");
            try {
                userViewController.doPost(req, resp);
            } catch (NullPointerException npe) {
                npe.fillInStackTrace();
            }
            assertThat(validate.findAll().stream().anyMatch(u -> u.getName().equals("allo")), is(true));
        }
    }

    @Test
    public void whenLoginTrueUserThenOK() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        try (MockedStatic<ValidateService> validateService = Mockito.mockStatic(ValidateService.class)) {
            validateService.when(ValidateService::getInstance).thenReturn(validate);
            HttpServletRequest req = mock(HttpServletRequest.class);
            HttpServletResponse resp = mock(HttpServletResponse.class);
            Mockito.when(req.getSession()).thenReturn(session);
            UserViewController userViewController = new UserViewController();
            SignInController signInController = new SignInController();
            userViewController.init();
            when(req.getParameter("login")).thenReturn("vas");
            when(req.getParameter("password")).thenReturn("vas");
            try {
                signInController.doPost(req, resp);
            } catch (NullPointerException npe) {
                npe.fillInStackTrace();
            }
            assertThat(session.getAttribute("login"), is("vas"));
        }
    }

    @Test
    public void whenLoginFakeUserThenOK() throws ServletException, IOException {
        Validate validate = new ValidateStub();
        try (MockedStatic<ValidateService> validateService = Mockito.mockStatic(ValidateService.class)) {
            validateService.when(ValidateService::getInstance).thenReturn(validate);
            HttpServletRequest req = mock(HttpServletRequest.class);
            HttpServletResponse resp = mock(HttpServletResponse.class);
            Mockito.when(req.getSession()).thenReturn(session);
            UserViewController userViewController = new UserViewController();
            SignInController signInController = new SignInController();
            userViewController.init();
            when(req.getParameter("login")).thenReturn("blabla");
            when(req.getParameter("password")).thenReturn("blabla");
            try {
                signInController.doPost(req, resp);
            } catch (NullPointerException npe) {
                npe.fillInStackTrace();
            }
            assertThat(session.getAttribute("login"), is(nullValue()));
        }
    }
}
