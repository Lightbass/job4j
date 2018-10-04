package ru.job4j.exam;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import java.util.Map;
import java.util.Properties;

/**
 * Класс - задача для исполнения парсинга вакансий и занесения их в БД.
 * @author Alexey Makarov
 * @since 04.10.2018
 * @version 0.1
 */
public class VacanciesJob implements Job {

    /**
     * Метод берет настройки из конфигурации, создает объект БД, проверяет в первый ли раз запускалась программа и
     * добавляет все вакансии подходящие под шаблон в настройках, либо начиная с начала года(или как указано в настройках),
     * либо за сегодня и вчера.
     * @param jobExecutionContext контекст из которого берется ссылка на конфигурацию.
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        Properties prop = (Properties) jobExecutionContext.getMergedJobDataMap().get("properties");
        VacanciesParser parser = new VacanciesParser(prop.getProperty("jsoup.siteUrl"),
                prop.getProperty("jsoup.query"),
                prop.getProperty("jsoup.queryDate"));
        try (VacanciesDatabase database = new VacanciesDatabase(prop)) {
            Map<String, String> vacancies;
            if (database.isFirstTime()) {
                vacancies = parser.getYearVacanies();
                database.setFirstTime(false);
            } else {
                vacancies = parser.getDayVacanies();
            }
            for (String link : vacancies.keySet()) {
                if (!database.isDuplicate(vacancies.get(link))) {
                    database.putVacancy(link, vacancies.get(link));
                }
            }
        } catch (Exception ex) {
            StartParser.LOGGER.error(ex.getMessage(), ex);
        }
    }
}
