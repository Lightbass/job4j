package ru.job4j.exam;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс - парсер вакансий с определенного сайта.
 * @author Alexey Makarov
 * @since 04.10.2018
 * @version 0.1
 */
public class VacanciesParser {
    private Elements vacancies;
    private Elements date;
    private String link;
    private String cssQuery;
    private String cssQueryDate;

    /**
     * Конструктор инициализирует поля ссылки на веб-страницу и запросов для нахождения нужной информации на странице.
     * @param link ссылка.
     * @param cssQuery запрос для нахождения нужной ссылки с текстом.
     * @param cssQueryDate запрос для нахождения даты постов.
     */
    public VacanciesParser(String link, String cssQuery, String cssQueryDate) {
        this.link = link;
        this.cssQuery = cssQuery;
        this.cssQueryDate = cssQueryDate;
    }

    /**
     * Обновление форума с указанием, какую страницу форума обновить.
     * @param n страница форума.
     * @param selectDate запрос по дате.
     */
    private void refresh(int n, String selectDate) {
        Document doc = null;
        try {
            doc = Jsoup.connect(link + n)
                    .userAgent("Mozilla")
                    .cookie("auth", "token")
                    .timeout(3000)
                    .get();
        } catch (IOException ioe) {
            StartParser.LOGGER.error(ioe.getMessage(), ioe);
        }
        vacancies = doc.select(cssQuery);
        date = doc.select(selectDate);
    }

    /**
     * Общий метод для загрузки нужных вакансий в Map.
     * @param selectDate ограничение по дате.
     * @return отображение со всеми вакансиями.
     */
    private Map<String, String> getVacancies(String selectDate) {
        HashMap<String, String> map = new HashMap<>();
        int n = 1;
        refresh(n, selectDate);
        while (!date.isEmpty()) {
            for (Element el : vacancies) {
                map.put(el.absUrl("href"), el.text());
            }
            n++;
            refresh(n, selectDate);
        }
        return map;
    }

    /**
     * Метод собирает все вакансии за последний год или
     * за другую дату(если пользователь укажет что-то другое в конфиге).
     * @return отображение со всеми вакансиями.
     */
    public Map<String, String> getYearVacanies() {
        StartParser.LOGGER.info("First execution.");
        StartParser.LOGGER.info("Creating DB and list vacancies since the beginning of year.");
        return getVacancies(cssQueryDate);
    }

    /**
     * Метод собирает все вакансии за вчера и сегодня.
     * @return отображение со всеми вакансиями.
     */
    public Map<String, String> getDayVacanies() {
        StartParser.LOGGER.info("Search vacancies and add to the existing DB.");
        return getVacancies(".altCol[style]:contains(сегодня), .altCol[style]:contains(вчера)");
    }
}
