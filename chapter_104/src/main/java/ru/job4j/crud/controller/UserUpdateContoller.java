package ru.job4j.crud.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.crud.model.User;
import ru.job4j.crud.service.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс - сервлет для отображения формы изменения пользователя.
 * @author Alexey Makarov
 * @since 14.10.2018
 * @version 0.1
 */
public class UserUpdateContoller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(UserUpdateContoller.class);
    private final ValidateService logic = ValidateService.getInstance();

    /**
     * Метод возвращает HTML страницу с заполняемыми полями, которые уже заполнены данными пользователя с конкретным
     * идентификатором.
     * @param req запрос, для заполнения полей данными пользователя с нужным идентификатором.
     * @param resp ответ страницей с полями.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("exists", false);
        try {
            User user = logic.findById(Integer.parseInt(req.getParameter("id")));
            if (user != null) {
                user.setPassword("");
                req.setAttribute("user", user);
                req.setAttribute("exists", true);
            }
        } catch (NumberFormatException nfe) {
            LOGGER.error(nfe.getMessage(), nfe);
        }
        req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);
    }
}
