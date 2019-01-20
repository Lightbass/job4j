package ru.job4j.crud.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.crud.service.Validate;
import ru.job4j.crud.service.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Класс - сервлет работающий с пользователями.
 * @author Alexey Makarov
 * @since 13.10.2018
 * @version 0.1
 */
public class UserViewController extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(UserViewController.class);
    private final Validate logic = ValidateService.getInstance();
    private final Map<String, Function<HttpServletRequest, Boolean>> dispatch = new HashMap<>();

    /**
     * В этом методе подготавливается объект для выбора действий.
     */
    @Override
    public void init() throws ServletException {
        dispatch.put("add",  add());
        dispatch.put("update", update());
        dispatch.put("delete", delete());
    }

    /**
     * В данном случае метод отвечает за вывод всех пользователей из хранилища построчно.
     * @param req любые параметры.
     * @param resp построчный вывод пользователей.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", logic.findAll());
        req.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(req, resp);
    }

    /**
     * Метод отвечает за добавление, изменение и удаление пользователей из хранилища.
     * @param req обязательно должен присутствовать правильный параметр action и опциональные параметры id, name, email,
     *            login.
     * @param resp сервер ответит, удалась операция или нет.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/result.jsp?result="
                + (makeAction(req) ? "1" : "0")).forward(req, resp);
    }

    /**
     * Метод реализует паттерн dispatch, передавая параметр action и выясняя, правильное значение action передано или
     * нет.
     * @param req запрос.
     * @return {@code true}, операция прошла успешно. {@code false}, произошла ошибка.
     */
    public boolean makeAction(HttpServletRequest req) {
        Function<HttpServletRequest, Boolean> func = dispatch.get(req.getParameter("action"));
        return func != null ? func.apply(req) : false;
    }

    /**
     * Метод для действия добавления пользователя.
     * @return функция добавления пользователя.
     */
    public Function<HttpServletRequest, Boolean> add() {
        return param -> {
            boolean result = false;
            String login = param.getParameter("login");
            HttpSession session = param.getSession();
            String userLogin = null;
            if (session != null) {
                userLogin = (String) param.getSession().getAttribute("login");
            }
            if (userLogin == null || logic.checkUserRole(userLogin)) {
                String name = param.getParameter("name");
                String email = param.getParameter("email");
                String password = param.getParameter("password");
                String country = param.getParameter("country");
                String city = param.getParameter("city");
                Boolean role = false;
                if (logic.checkUserRole(userLogin)) {
                    role = param.getParameter("role").contains("true");
                }
                result = logic.add(name, login, password, email, role, country, city);
            }
            return result;
        };
    }

    /**
     * Метод для действия обновления пользователя.
     * @return функция обновления пользователя.
     */
    public Function<HttpServletRequest, Boolean> update() {
        return param -> {
            boolean result = false;
            int id = -1;
            String login = param.getParameter("login");
            HttpSession session = param.getSession();
            String userLogin = null;
            if (session != null) {
                userLogin = (String) param.getSession().getAttribute("login");
            }
            if (login.equals(userLogin) || logic.checkUserRole(userLogin)) {
                try {
                    id = Integer.parseInt(param.getParameter("id"));
                    String name = param.getParameter("name");
                    String email = param.getParameter("email");
                    String password = param.getParameter("password");
                    String country = param.getParameter("country");
                    String city = param.getParameter("city");
                    Boolean role = false;
                    if (logic.checkUserRole(userLogin)) {
                        role = param.getParameter("role").contains("true");
                    }
                    result = logic.update(id, name, login, password, email, role, country, city);
                } catch (NumberFormatException nfe) {
                    LOGGER.error(nfe.getMessage(), nfe);
                }
            }
            return result;
        };
    }

    /**
     * Метод для действия удаления пользователя.
     * @return функция удаления пользователя.
     */
    public Function<HttpServletRequest, Boolean> delete() {
        return param -> {
            boolean result = false;
            int id = -1;
            String userLogin = (String) param.getSession().getAttribute("login");
            if (logic.checkUserRole(userLogin)) {
                try {
                    id = Integer.parseInt(param.getParameter("id"));
                    result = logic.delete(id);
                } catch (NumberFormatException nfe) {
                    LOGGER.error(nfe.getMessage(), nfe);
                }
            }
            return result;
        };
    }
}
