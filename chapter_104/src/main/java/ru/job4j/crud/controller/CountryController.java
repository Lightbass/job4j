package ru.job4j.crud.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.crud.model.Country;
import ru.job4j.crud.service.Validate;
import ru.job4j.crud.service.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

/**
 * Класс - сервлет для отображения формы создания пользователя.
 * @author Alexey Makarov
 * @since 14.10.2018
 * @version 0.1
 */
public class CountryController extends HttpServlet {

    private final Validate logic = ValidateService.getInstance();
    private static final Logger LOGGER = LogManager.getLogger(CountryController.class);
    private final ObjectMapper mapper = new ObjectMapper();

    /**
     * Метод возвращает HTML страницу с заполняемыми полями, для создания пользователя.
     * @param req запрос.
     * @param resp ответ страницей с полями.
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        encodeJson(resp.getOutputStream(), logic.findAllCountries());
    }

    private void encodeJson(OutputStream out, Collection<Country> countries) {
        try {
            mapper.writeValue(out, countries);
        } catch (IOException ioe) {
            LOGGER.error(ioe.getMessage(), ioe);
        }
    }
}
