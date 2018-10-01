package ru.job4j.magnet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс - для работы с БД на основе sqlite.
 * @author Alexey Makarov
 * @since 01.10.2018
 * @version 0.1
 */
public class StoreSQL implements AutoCloseable {
    private final static Logger LOG = LoggerFactory.getLogger(StoreSQL.class);
    private Connection connection;
    private Config config;

    /**
     * Конструктор инициализирует подключение к БД с помощью конфигурации.
     * @param config параметры подключения к БД.
     */
    public StoreSQL(Config config) {
        this.config = config;
        try {
            connection = DriverManager.getConnection(config.getUrlConnection());
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            LOG.error(ex.getMessage(), ex);
        }
        try (Statement statement = connection.createStatement()) {
            statement.execute(config.getQueryCreateTable());
        } catch (SQLException ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    /**
     * Генерация в БД n записей.
     * @param n количество записей.
     */
    public void generate(int n) {
        try (Statement statement = connection.createStatement();
             PreparedStatement prepStatement = connection.prepareStatement(config.getQueryInsert())) {
            statement.execute(config.getQueryDeleteAll());
            for (int i = 1; i != n + 1; i++) {
                prepStatement.setInt(1, i);
                prepStatement.execute();
            }
            connection.commit();
        } catch (SQLException ex) {
            LOG.error(ex.getMessage(), ex);
            try {
                connection.rollback();
            } catch (SQLException sqlEx) {
                LOG.error(sqlEx.getMessage(), sqlEx);
            }
        }
    }

    /**
     * Метод возвращает список значений из БД.
     * @return список с полями.
     */
    public List<Entry> getEntriesFromDatabase() {
        List<Entry> list = new LinkedList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(config.getQuerySelectAll());
            while (rs.next()) {
                list.add(new Entry(rs.getInt("field")));
            }
        } catch (SQLException ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return list;
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }
}
