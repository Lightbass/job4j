package ru.job4j.crud.repository;

import ru.job4j.crud.model.City;
import ru.job4j.crud.model.Country;
import ru.job4j.crud.model.User;

import java.util.Collection;

/**
 * Интерфейс - хранилище пользователей.
 * @author Alexey Makarov
 * @since 13.10.2018
 * @version 0.1
 */
public interface Store {
    /**
     * Метод добавляет пользователя в хранилище.
     * @param user добавляемый пользователь.
     */
    void add(User user);

    /**
     * Метод обновляет пользователя в хранилище.
     * @param id идентификатор обновляемого пользователя.
     * @param user пользователь, который встанет на его место.
     */
    void update(int id, User user);

    /**
     * Метод удаляет пользователя из хранилища.
     * @param id идентификатор удаляемого пользователя.
     */
    void delete(int id);

    /**
     * Метод возвращает всех пользователей в хранилище.
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
     * Метод возвращает все страны в хранилище.
     * @return все страны.
     */
    Collection<Country> findAllCountries();

    /**
     * Метод возвращает все города в конкретной стране из хранилища.
     * @param countryId идентификатор страны.
     * @return все города в данной стране.
     */
    Collection<City> findCitiesByCountryId(Long countryId);
}
