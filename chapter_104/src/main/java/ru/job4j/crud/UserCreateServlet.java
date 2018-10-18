package ru.job4j.crud;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Класс - сервлет для отображения формы создания пользователя.
 * @author Alexey Makarov
 * @since 14.10.2018
 * @version 0.1
 */
public class UserCreateServlet extends HttpServlet {
    /**
     * Метод возвращает HTML страницу с заполняемыми полями, для создания пользователя.
     * @param req запрос.
     * @param resp ответ страницей с полями.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/create.jsp").forward(req, resp);
    }
}
