package ru.job4j.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Класс - сервлет для отображения формы изменения пользователя.
 * @author Alexey Makarov
 * @since 14.10.2018
 * @version 0.1
 */
public class UserUpdateServlet extends HttpServlet {
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
        User user = null;
        req.setAttribute("exists", false);
        try {
            user = logic.findById(Integer.parseInt(req.getParameter("id")));
            req.setAttribute("user", user);
            if (user != null) {
                req.setAttribute("exists", true);
            }
        } catch (NumberFormatException nfe) {
            UserServlet.LOGGER.error(nfe.getMessage(), nfe);
        }
        req.getRequestDispatcher("/WEB-INF/views/update.jsp").forward(req, resp);
    }
}
