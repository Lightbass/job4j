package ru.job4j.trackerbase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.tracker.StartUI;
import ru.job4j.tracker.data.Item;
import ru.job4j.tracker.data.ItemContainer;
import ru.job4j.tracker.input.ConsoleInput;
import ru.job4j.tracker.input.ValidateInput;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;

/**
 * Класс - меню для работы с базой данных postgresql.
 * @author Alexey Makarov
 * @since 27.09.2018
 * @version 0.1
 */
public class TrackerDatabase implements ItemContainer, AutoCloseable {
    private final static Logger LOG = LoggerFactory.getLogger(TrackerDatabase.class);
    private final Random r = new Random();
    private final Properties properties;
    private final String urlConnection;
    private final String urlDBConnection;
    private final String existsDB;
    private final String createDB;
    private final String createTable;
    private final String insertQuery;
    private final String selectAllQuery;
    private final String selectByIdQuery;
    private final String selectByNameQuery;
    private final String updateQuery;
    private final String deleteQuery;
    private Connection connection;

    /**
     * Конструктор инициализирует переменные запросов и запускает метод подключения к БД.
     */
    public TrackerDatabase() {
        properties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("trackerbase/db.properties")) {
            properties.load(inputStream);
        } catch (IOException ioe) {
            LOG.error(ioe.getMessage(), ioe);
        }
        urlConnection = properties.getProperty("db.urlConnection");
        urlDBConnection = properties.getProperty("db.urlDBConnection");
        existsDB = properties.getProperty("query.existsDB");
        createDB = properties.getProperty("query.createDB");
        createTable = properties.getProperty("query.createTable");
        insertQuery = properties.getProperty("query.insert");
        selectAllQuery = properties.getProperty("query.selectAll");
        selectByIdQuery = properties.getProperty("query.selectById");
        selectByNameQuery = properties.getProperty("query.selectByName");
        updateQuery = properties.getProperty("query.update");
        deleteQuery = properties.getProperty("query.delete");
        createAndConnect();
    }

    /**
     * Метод подключается к базе данных, создаёт базу, если её нет и создаёт таблицу, если её нет.
     */
    private void createAndConnect() {
        try (Connection serverConnection = DriverManager.getConnection(urlConnection, properties);
             Statement servStatement = serverConnection.createStatement()) {
            ResultSet exists = servStatement.executeQuery(existsDB);
            if (exists.next()) {
                if (!exists.getBoolean(1)) {
                    servStatement.execute(createDB);
                }
            }
            connection = DriverManager.getConnection(urlDBConnection, properties);
            try (Statement dbStatement = connection.createStatement()) {
                dbStatement.execute(createTable);
            }
        } catch (SQLException ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    /**
     * Запуск UI для проверки трекера с БД на деле.
     * @param args аргрументы.
     */
    public static void main(String[] args) {
        try (TrackerDatabase tracker = new TrackerDatabase()) {
            new StartUI(new ValidateInput(new ConsoleInput()), tracker).init();
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }

    /**
     * Метод реализаущий добавление заявки в хранилище.
     * @param item новая заявка.
     * @return возвращает добавленную заявку.
     */
    private Item add(Item item, long id) {
        item.setId(id);
        try (PreparedStatement prepStat = connection.prepareStatement(insertQuery)) {
            prepStat.setLong(1, item.getId());
            prepStat.setString(2, item.getName());
            prepStat.setString(3, item.getDescription());
            prepStat.execute();
        } catch (SQLException ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return item;
    }

    /**
     * Метод добавляет заявку в хранилище.
     * @param item новая заявка
     * @return добавленная заявка.
     */
    public Item add(Item item) {
        return add(item, this.generateId());
    }

    /**
     * Вывод массива со всеми заявками.
     * @return массив с заявками.
     */
    public List<Item> findAll() {
        List<Item> list = new ArrayList<>();
        try (Statement prepStat = connection.createStatement()) {
            ResultSet all = prepStat.executeQuery(selectAllQuery);
            while (all.next()) {
                list.add(new Item(
                        all.getString("name"),
                        all.getString("description"),
                        all.getLong("id")
                ));
            }
        } catch (SQLException ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return list;
    }

    /**
     * Найти заявки с указанным именем и вывод их в качестве массива.
     * @param key искомое имя в заявках.
     * @return массив заявок с данным именем.
     */
    public List<Item> findByName(String key) {
        List<Item> list = new ArrayList<>();
        try (PreparedStatement prepStat = connection.prepareStatement(selectByNameQuery)) {
            prepStat.setString(1, key);
            ResultSet all = prepStat.executeQuery();
            while (all.next()) {
                list.add(new Item(
                        all.getString("name"),
                        all.getString("description"),
                        all.getLong("id")
                ));
            }
        } catch (SQLException ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return list;
    }

    /**
     * Найти заявку с указанным идентификатором.
     * @param id идентификатор искомой заявки.
     * @return заявка.
     */
    public Item findById(long id) {
        Item item = null;
        try (PreparedStatement prepStat = connection.prepareStatement(selectByIdQuery)) {
            prepStat.setLong(1, id);
            ResultSet all = prepStat.executeQuery();
            if (all.next()) {
                item = new Item(
                        all.getString("name"),
                        all.getString("description"),
                        all.getLong("id")
                );
            }
        } catch (SQLException ex) {
            LOG.error(ex.getMessage(), ex);
        }
        return item;

    }

    /**
     * Замена/редактирование заявки с указанным айди, на другую.
     * @param id идентификатор заявки, которую нужно заменить/редактировать.
     * @param item заявка на которую нужно заменить.
     */
    public boolean replace(long id, Item item) {
        boolean result = false;
        Item prev = findById(id);
        if (prev != null) {
            try (PreparedStatement prepStat = connection.prepareStatement(updateQuery)) {
                prepStat.setString(1, item.getName());
                prepStat.setString(2, item.getDescription());
                prepStat.setLong(3, id);
                item.setId(id);
                prepStat.execute();
                result = true;
            } catch (SQLException ex) {
                LOG.error(ex.getMessage(), ex);
            }
        }
        return result;
    }

    /**
     * Удаление заявки из трекера.
     * @param id идентификатор заявки.
     */
    public boolean delete(long id) {
        boolean result = false;
        Item prev = findById(id);
        if (prev != null) {
            try (PreparedStatement prepStat = connection.prepareStatement(deleteQuery)) {
                prepStat.setLong(1, id);
                result = prepStat.execute();
            } catch (SQLException ex) {
                LOG.error(ex.getMessage(), ex);
            }
        }
        return result;
    }

    /**
     * Генерация уникального идентификатора.
     * @return идентификатор.
     */
    private long generateId() {
        long result = r.nextLong();
        while (findById(result) != null) {
            result = r.nextLong();
        }
        return result;
    }

    /**
     * Метод удаляет таблицу и закрывает соединение с бд.
     * @throws SQLException если какая-то проблема с соединением к бд или таблице, не существует, например.
     */
    @Override
    public void close() {
        try (Statement stat = connection.createStatement()) {
            stat.execute("DROP TABLE item");
            connection.close();
        } catch (SQLException ex) {
            LOG.error(ex.getMessage(), ex);
        }
    }
}
