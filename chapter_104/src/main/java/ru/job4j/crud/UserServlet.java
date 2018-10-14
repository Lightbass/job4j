package ru.job4j.crud;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.servlets.EchoServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * Класс - сервлет работающий с пользователями.
 * @author Alexey Makarov
 * @since 13.10.2018
 * @version 0.1
 */
public class UserServlet extends HttpServlet {
    public static final Logger LOGGER = LogManager.getLogger(EchoServlet.class);
    private final ValidateService logic = ValidateService.getInstance();
    private final Map<String, Function<HttpServletRequest, Boolean>> dispatch = new HashMap<>();
    private final String ls = System.lineSeparator();

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
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        List<User> list = logic.findAll();
        StringBuilder sb = new StringBuilder("<!DOCTYPE html> <html lang=\"en\">"
                + "<head><meta charset=\"UTF-8\">"
                + "<title>Users</title>"
                + "</head><body><table>");
        for (User user : list) {
            sb.append(String.format("<tr><td>Name = %s, Login = %s, e-mail = %s, id = %d, date = %s</td>",
                    user.getName(), user.getLogin(), user.getEmail(), user.getId(), user.getCreateDate()));
            sb.append("<td><form>");
            sb.append("<button formaction='" + req.getContextPath() + "/edit"
                    + "' formmethod='get' name='id' value='" + user.getId() + "'>Edit</button>");
            sb.append("<button formaction='" + req.getContextPath() + "/list?action=delete&id=" + user.getId()
                    + "' formmethod='post'>Delete</button>");
            sb.append("</form></td></tr>");
        }
        sb.append("</table><br><form>");
        sb.append("<button formaction='" + req.getContextPath() + "/create' formmethod='get'>Create user</button>");
        sb.append("</form></body></html>");
        writer.append(sb.toString());
        writer.flush();
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
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.append("<!DOCTYPE html> <html lang='en'>"
                + "<head><meta charset='UTF-8'>"
                + "<title>Result</title>"
                + "<meta http-equiv='refresh' content='2; url=" + req.getContextPath() + "/list'>"
                + "</head><body>");
        writer.append(makeAction(req) ? "Action done" : "Action error");
        writer.append("</body></html>");
        writer.flush();
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
            String name = param.getParameter("name");
            String login = param.getParameter("login");
            String email = param.getParameter("email");
            return logic.add(name, login, email);
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
            try {
                id = Integer.parseInt(param.getParameter("id"));
                String name = param.getParameter("name");
                String login = param.getParameter("login");
                String email = param.getParameter("email");
                result = logic.update(id, name, login, email);
            } catch (NumberFormatException nfe) {
                LOGGER.error(nfe.getMessage(), nfe);
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
            try {
                id = Integer.parseInt(param.getParameter("id"));
                result = logic.delete(id);
            } catch (NumberFormatException nfe) {
                LOGGER.error(nfe.getMessage(), nfe);
            }
            return result;
        };
    }
}
