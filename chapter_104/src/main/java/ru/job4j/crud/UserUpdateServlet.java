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
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        StringBuilder sb = new StringBuilder("<!DOCTYPE html> <html lang=\"en\">"
                + "<head><meta charset=\"UTF-8\">"
                + "<title>Users</title>");
        try {
            user = logic.findById(Integer.parseInt(req.getParameter("id")));
            sb.append("</head><body><form action='" + req.getContextPath() + "/list' method='post'>"
                    + "Name : <input type='text' name='name' value='" + user.getName() + "'/><br>"
                    + "Login : <input type='text' name='login' value='" + user.getLogin() + "'/><br>"
                    + "e-mail : <input type='text' name='email' value='" + user.getEmail() + "'/><br>"
                    + "<input type='hidden' name='id' value='" + user.getId() + "'>"
                    + "<input type='submit' name='action' value='update'><br>"
                    + "</form>");

        } catch (NumberFormatException | NullPointerException nfe) {
            UserServlet.LOGGER.error(nfe.getMessage(), nfe);
            sb.append("<meta http-equiv='refresh' content='2; url=" + req.getContextPath() + "/list'></head>"
                    + "<body>Invalid ID");

        }
        sb.append("</body></html>");
        writer.append(sb.toString());
        writer.flush();
    }
}
