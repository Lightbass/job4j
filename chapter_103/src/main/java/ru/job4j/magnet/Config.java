package ru.job4j.magnet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Класс задающий конфигурацию для подключения к БД.
 * @author Alexey Makarov
 * @since 01.10.2018
 * @version 0.1
 */
public class Config {
    private final static Logger LOG = LoggerFactory.getLogger(Config.class);
    private String urlConnection;
    private String queryInsert;
    private String createTable;
    private String deleteAll;
    private String selectAll;

    /**
     * Конструктор инициализирует поля запросов.
     */
    public Config() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("magnet/magnetDB.properties")) {
            Properties prop = new Properties();
            prop.load(is);
            urlConnection = prop.getProperty("db.urlDBConnection");
            queryInsert = prop.getProperty("query.insert");
            createTable = prop.getProperty("query.createTable");
            deleteAll = prop.getProperty("query.deleteAll");
            selectAll = prop.getProperty("query.selectAll");
        } catch (IOException ioe) {
            LOG.error(ioe.getMessage(), ioe);
        }
    }

    public String getUrlConnection() {
        return urlConnection;
    }

    public String getQueryCreateTable() {
        return createTable;
    }

    public String getQueryInsert() {
        return queryInsert;
    }

    public String getQueryDeleteAll() {
        return deleteAll;
    }

    public String getQuerySelectAll() {
        return selectAll;
    }
}
