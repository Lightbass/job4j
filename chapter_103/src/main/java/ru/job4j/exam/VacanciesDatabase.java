package ru.job4j.exam;

import java.sql.*;
import java.util.Properties;

/**
 * Класс для работы с базой данных вакансий.
 * @author Alexey Makarov
 * @since 04.10.2018
 * @version 0.1
 */
public class VacanciesDatabase implements AutoCloseable {
    private Connection connection;
    private final Properties properties;
    private final String urlConnection;
    private final String urlDBConnection;
    private final String existsDB;
    private final String createDB;
    private final String createTable;
    private final String insertQuery;
    private final String checkDuplicate;
    private boolean isFirstTime = false;

    /**
     * Конструктор инициирует поля подключения и запросов, загружая их из настроек и вызывает метод для создания БД и
     * подключения к БД. Также загружает драйвер для работы с БД.
     * @param properties конфигурация со всеми настройками.
     * @throws ClassNotFoundException если в конфигурации указан неверный драйвер для БД.
     */
    public VacanciesDatabase(Properties properties) throws ClassNotFoundException {
        Class.forName(properties.getProperty("jdbc.driver"));
        this.properties = properties;
        urlConnection = properties.getProperty("jdbc.urlConnection");
        urlDBConnection = properties.getProperty("jdbc.urlDBConnection");
        existsDB = properties.getProperty("query.existsDB");
        createDB = properties.getProperty("query.createDB");
        createTable = properties.getProperty("query.createTable");
        insertQuery = properties.getProperty("query.insert");
        checkDuplicate = properties.getProperty("query.check");
        createAndConnect();
    }

    /**
     * Метод создает базу, если её нет, и создаёт таблицу, если её нет.
     * И делает подключение к базе, с которой предстоит работать в будущем.
     */
    private void createAndConnect() {
        try (Connection serverConnection = DriverManager.getConnection(urlConnection, properties);
             Statement servStatement = serverConnection.createStatement()) {
            ResultSet exists = servStatement.executeQuery(existsDB);
            if (exists.next()) {
                if (!exists.getBoolean(1)) {
                    servStatement.execute(createDB);
                    isFirstTime = true;
                }
            }
            connection = DriverManager.getConnection(urlDBConnection, properties);
            try (Statement dbStatement = connection.createStatement()) {
                dbStatement.execute(createTable);
            }
        } catch (SQLException ex) {
            StartParser.LOGGER.error(ex.getMessage(), ex);
        }
    }

    /**
     * Метод проверяет, содержится ли вакансия с указанным текстом в БД.
     * @param text текст вакансии.
     * @return {@code false}, если вакансии с таким текстом нет в базе. {@code true} если есть.
     */
    public boolean isDuplicate(String text) {
        boolean result = false;
        try (PreparedStatement st = connection.prepareStatement(checkDuplicate)) {
            st.setString(1, text);
            ResultSet exists = st.executeQuery();
            if (exists.next()) {
                if (exists.getBoolean(1)) {
                    result = true;
                }
            }
        } catch (SQLException ex) {
            StartParser.LOGGER.error(ex.getMessage(), ex);
        }
        return result;
    }

    /**
     * Добавляет запись в базу с указанной ссылкой и текстом.
     * @param link ссылка на вакансию.
     * @param text текст вакансии.
     */
    public void putVacancy(String link, String text) {
        try (PreparedStatement st = connection.prepareStatement(insertQuery)) {
            st.setString(1, link);
            st.setString(2, text);
            st.execute();
        } catch (SQLException ex) {
            StartParser.LOGGER.error(ex.getMessage(), ex);
        }
    }

    /**
     * Метод возвращает значение, которое говорит о том, в первый ли раз запускается программа.
     * @return {@code true} программа запускается в первый раз. {@code false} программа уже ранее запускалась.
     */
    public boolean isFirstTime() {
        return isFirstTime;
    }

    /**
     * Меняет значение флага первого запуска программы.
     * @param isFirstTime новый флаг.
     */
    public void setFirstTime(boolean isFirstTime) {
        this.isFirstTime = isFirstTime;
    }

    /**
     * Метод закрывает соединение с БД.
     * @throws Exception если что-то пошло не так.
     */
    @Override
    public void close() throws Exception {
        connection.close();
    }
}
