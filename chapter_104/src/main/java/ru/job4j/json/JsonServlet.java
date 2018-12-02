package ru.job4j.json;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class JsonServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(JsonServlet.class);
    private final JsonService jsonService = JsonService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json; charset=UTF-8");
        jsonService.encodeJson(resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder sb = new StringBuilder();
        reader.lines().forEach(sb::append);
        try {
            jsonService.parseJson(sb.toString());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }
}
