package ru.job4j.exam;

import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import org.apache.log4j.Logger;

/**
 * Класс - стартер парсера.
 * @author Alexey Makarov
 * @since 04.10.2018
 * @version 0.1
 */
public class StartParser {
    private final Properties properties = new Properties();
    static final Logger LOGGER = LogManager.getLogger(StartParser.class);

    /**
     * Конструктор конфигурирует парсер на основе пользовательского конфига, который указан в параметрах.
     * @param configPath путь к пользовательскому конфигу.
     */
    public StartParser(String configPath) {
        PropertyConfigurator.configure(getClass().getClassLoader().getResourceAsStream("exam/log4j.properties"));
        LOGGER.info("Start parser");
        try {
            if (configPath == null) {
                LOGGER.info("Loading default config");
                properties.load(getClass().getClassLoader().getResourceAsStream("exam/app.properties"));

            } else {
                LOGGER.info("Loading user config");
                InputStream is = new FileInputStream(configPath);
                properties.load(is);
                is.close();
            }
        } catch (IOException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("properties", properties);
        JobDetail job = JobBuilder.newJob(VacanciesJob.class)
                .withIdentity("myJob", "group1")
                .usingJobData(jobDataMap)
                .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(cronSchedule(properties.getProperty("cron.time")))
                .forJob("myJob", "group1")
                .build();
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(job, trigger);
            scheduler.triggerJob(job.getKey());
            scheduler.start();
        } catch (SchedulerException se) {
            LOGGER.error(se.getMessage(), se);
        }
    }

    /**
     * Конструктор для работы со стандартным конфигом.
     */
    public StartParser() {
        this(null);
    }

    /**
     * Метод запускает парсер.
     * @param args параметры.
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            new StartParser();
        } else {
            new StartParser(args[0]);
        }
    }
}
