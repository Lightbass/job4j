package ru.job4j.servlets;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class EchoServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(EchoServlet.class);
    private List<String> users = new CopyOnWriteArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        StringBuilder sb = new StringBuilder("<table>");
        for (String login : users) {
            sb.append("<tr><td>" + login + "</td></tr>");
        }
        sb.append("</table>");

        writer.append("<!DOCTYPE html> <html lang=\"en\">"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<title>Title</title>"
                + "</head>"
                + "<body>"
                + "<form action='" + req.getContextPath() + "/echo' method='post'>"
                + "Name : <input type='text' name='login'/>"
                + "<input type='submit'>"
                + "</form>"
                + "<br/>"
                + sb.toString()
                + "</body>"
                + "</html>");
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        this.users.add(req.getParameter("login"));
        doGet(req, resp);
    }
}
