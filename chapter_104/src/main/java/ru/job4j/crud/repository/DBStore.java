package ru.job4j.crud.repository;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.job4j.crud.model.City;
import ru.job4j.crud.model.Country;
import ru.job4j.crud.model.User;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Properties;

/**
 * Класс - реализация хранилища пользователей в БД, синглтон.
 * @author Alexey Makarov
 * @since 13.10.2018
 * @version 0.1
 */
public class DBStore implements Store {
    private static final Logger LOGGER = LogManager.getLogger(DBStore.class);
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DBStore SINGLETON_INSTANCE = new DBStore();
    private final Properties properties = new Properties();
    private String insertQuery;
    private String selectQuery;
    private String findByIdQuery;
    private String findByLogin;
    private String updateQuery;
    private String deleteQuery;
    private String findAllCountries;
    private String findCitiesByCountryId;

    /**
     * Конструктор инициализирует БД.
     */
    private DBStore() {
        initProperties();
        try (Connection serverConnection =
                     DriverManager.getConnection(properties.getProperty("jdbc.urlConnection"), properties);
             Statement serverStatement = serverConnection.createStatement()) {
            ResultSet exists = serverStatement.executeQuery(properties.getProperty("query.existsDB"));
            if (exists.next()) {
                if (!exists.getBoolean(1)) {
                    serverStatement.execute(properties.getProperty("query.createDB"));
                }
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        try (Connection serverConnection = SOURCE.getConnection();
             Statement serverStatement = serverConnection.createStatement()) {
            serverStatement.execute(properties.getProperty("query.createUserTable"));
            serverStatement.execute(properties.getProperty("query.createCountryTable"));
            serverStatement.execute(properties.getProperty("query.createCityTable"));
            serverStatement.execute(properties.getProperty("query.initCountryTable"));
            serverStatement.execute(properties.getProperty("query.initCityTable"));
        }
        catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    /**
     * Метод инициализирует параметры из файла и настраивает компоненты с помощью них.
     */
    private void initProperties() {
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("crud/app.properties"));
            Class.forName(properties.getProperty("jdbc.driver"));
        } catch (IOException | ClassNotFoundException | NoClassDefFoundError ioe) {
            LOGGER.error(ioe.getMessage(), ioe);
        }
        insertQuery = properties.getProperty("query.insert");
        selectQuery = properties.getProperty("query.select");
        findByIdQuery = properties.getProperty("query.findById");
        findByLogin = properties.getProperty("query.findByLogin");
        updateQuery = properties.getProperty("query.update");
        deleteQuery = properties.getProperty("query.delete");
        findAllCountries = properties.getProperty("query.findAllCountries");
        findCitiesByCountryId = properties.getProperty("query.findCitiesByCountryId");
        SOURCE.setUrl(properties.getProperty("jdbc.urlConnection"));
        SOURCE.setUsername(properties.getProperty("user"));
        SOURCE.setPassword(properties.getProperty("password"));
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
        SOURCE.setUrl(properties.getProperty("jdbc.urlDBConnection"));
    }

    public static DBStore getInstance() {
        return SINGLETON_INSTANCE;
    }

    /**
     * Метод добавляет пользователя в хранилище.
     * @param user добавляемый пользователь.
     */
    @Override
    public void add(User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(insertQuery)) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getPassword());
            st.setString(4, user.getEmail());
            st.setTimestamp(5, new Timestamp(user.getCreateDate().getTime()));
            st.setBoolean(6, user.getRole());
            st.setString(7, user.getCountry());
            st.setString(8, user.getCity());
            st.execute();
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    /**
     * Метод обновляет пользователя в хранилище.
     * @param id идентификатор обновляемого пользователя.
     * @param user пользователь, который встанет на его место.
     */
    @Override
    public void update(int id, User user) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(updateQuery)) {
            st.setString(1, user.getName());
            st.setString(2, user.getLogin());
            st.setString(3, user.getPassword());
            st.setString(4, user.getEmail());
            st.setBoolean(5, user.getRole());
            st.setString(6, user.getCountry());
            st.setString(7, user.getCity());
            st.setInt(8, id);
            st.execute();
            user.setId(id);
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    /**
     * Метод удаляет пользователя из хранилища.
     * @param id идентификатор удаляемого пользователя.
     */
    @Override
    public void delete(int id) {
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(deleteQuery)) {
            st.setInt(1, id);
            st.execute();
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    /**
     * Метод возвращает всех пользователей в хранилище.
     * @return все пользователи.
     */
    @Override
    public Collection<User> findAll() {
        Collection<User> users = null;
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(selectQuery);
            users = new LinkedList<>();
            while (rs.next()) {
                users.add(new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getTimestamp(6).getTime(),
                        rs.getBoolean(7),
                        rs.getString(8),
                        rs.getString(9)));
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return users;
    }

    /**
     * Метод ищет пользователя в хранилище по идентификатору и возвращает его.
     * @param id идентификатор.
     * @return пользователь.
     */
    @Override
    public User findById(int id) {
        User user = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(findByIdQuery)) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getTimestamp(6).getTime(),
                        rs.getBoolean(7),
                        rs.getString(8),
                        rs.getString(9));
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return user;
    }

    /**
     * Метод ищет пользователя в хранилище по логину и возвращает его.
     * @param login логин.
     * @return пользователь.
     */
    @Override
    public User findByLogin(String login) {
        User user = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(findByLogin)) {
            st.setString(1, login);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                user = new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getTimestamp(6).getTime(),
                        rs.getBoolean(7),
                        rs.getString(8),
                        rs.getString(9));
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return user;
    }

    /**
     * Метод возвращает всех пользователей в хранилище.
     * @return все пользователи.
     */
    @Override
    public Collection<Country> findAllCountries() {
        Collection<Country> countries = null;
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.createStatement()) {
            ResultSet rs = st.executeQuery(findAllCountries);
            countries = new LinkedList<>();
            while (rs.next()) {
                countries.add(new Country(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return countries;
    }

    /**
     * Метод возвращает всех пользователей в хранилище.
     * @return все пользователи.
     */
    @Override
    public Collection<City> findCitiesByCountryId(Long countryId) {
        Collection<City> countries = null;
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement st = connection.prepareStatement(findCitiesByCountryId)) {
            st.setLong(1, countryId);
            ResultSet rs = st.executeQuery();
            countries = new LinkedList<>();
            while (rs.next()) {
                countries.add(new City(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
        return countries;
    }
}
