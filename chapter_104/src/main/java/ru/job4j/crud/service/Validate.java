package ru.job4j.crud.service;

import ru.job4j.crud.model.City;
import ru.job4j.crud.model.Country;
import ru.job4j.crud.model.User;

import java.util.Collection;

/**
 * Интерфейс для сервиса ,обрабатывает поступающую информацию.
 * @author Alexey Makarov
 * @since 13.10.2018
 * @version 0.1
 */
public interface Validate {

    /**
     * Метод добавляет пользователя в хранилище.
     * @param name имя пользователя.
     * @param login логин.
     * @param email почта.
     * @return {@code true}, операция прошла успешно. {@code false}, произошла ошибка.
     */
    boolean add(String name, String login, String password, String email, Boolean role, String country, String city);

    /**
     * Метод обновляет пользователя в хранилище.
     * @param id идентификатор пользователя.
     * @param name имя пользователя.
     * @param login логин.
     * @param email почта.
     * @return {@code true}, операция прошла успешно. {@code false}, произошла ошибка.
     */
    boolean update(int id, String name, String login, String password, String email, Boolean role, String country, String city);

    /**
     * Метод удаляет пользователя в хранилище.
     * @param id идентификатор пользователя.
     * @return {@code true}, операция прошла успешно. {@code false}, произошла ошибка.
     */
    boolean delete(int id);

    /**
     * Метод возвращает всех пользователей внутри хранилища.
     * @return все пользователи.
     */
    Collection<User> findAll();

    /**
     * Метод ищет пользователя в хранилище по идентификатору и возвращает его.
     * @param id идентификатор.
     * @return пользователь.
     */
    User findById(int id);

    /**
     * Метод ищет пользователя в хранилище по логину и возвращает его.
     * @param login логин.
     * @return пользователь.
     */
    User findByLogin(String login);

    /**
     * Метод возвращает все страны внутри хранилища.
     * @return все страны.
     */
    Collection<Country> findAllCountries();

    /**
     * Метод возвращает все страны внутри хранилища.
     * @return все страны.
     */
    Collection<City> findCitiesByCountryId(Long countryId);

    /**
     * Метод проверяет, есть ли у данного пользователя права администратора.
     * @param login пользователь.
     * @return {@code true} пользователь является администратором, {@code false} он обычный пользователь.
     */
    boolean checkUserRole(String login);

    /**
     * Метод ищет пользователя с данным логином и паролем для аутентификации.
     * @param login логин.
     * @param password пароль.
     * @return {@code true}, если логин и пароль верные. {@code false}, если нет.
     */
    boolean signIn(String login, String password);
}
