package ru.job4j.crud;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс логики, обрабатывает поступающую информацию, синглтон.
 * @author Alexey Makarov
 * @since 13.10.2018
 * @version 0.1
 */
public class ValidateService {
    private static final ValidateService SINGLETON_INSTANCE = new ValidateService();
    private static final MemoryStore STORE = MemoryStore.getInstance();

    /**
     * Приватный конструктор для реализации синглтона.
     */
    private ValidateService() {

    }

    /**
     * Возвращает единственный объект этого класса.
     * @return объект.
     */
    public static ValidateService getInstance() {
        return SINGLETON_INSTANCE;
    }

    /**
     * Метод добавляет пользователя в хранилище.
     * @param name имя пользователя.
     * @param login логин.
     * @param email почта.
     * @return {@code true}, операция прошла успешно. {@code false}, произошла ошибка.
     */
    public boolean add(String name, String login, String email) {
        boolean result = false;
        if (STORE.findByLogin(login) == null) {
            if (validateEmail(email)) {
                STORE.add(new User(name, login, email));
                result = true;
            }
        }
        return result;
    }

    /**
     * Метод обновляет пользователя в хранилище.
     * @param id идентификатор пользователя.
     * @param name имя пользователя.
     * @param login логин.
     * @param email почта.
     * @return {@code true}, операция прошла успешно. {@code false}, произошла ошибка.
     */
    public boolean update(int id, String name, String login, String email) {
        boolean result = false;
        if (STORE.findById(id) != null) {
            if (STORE.findByLogin(login) == null || login == null) {
                if (validateEmail(email) || email == null) {
                    STORE.update(id, new User(name, login, email));
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * Метод удаляет пользователя в хранилище.
     * @param id идентификатор пользователя.
     * @return {@code true}, операция прошла успешно. {@code false}, произошла ошибка.
     */
    public boolean delete(int id) {
        boolean result = false;
        if (STORE.findById(id) != null) {
            STORE.delete(id);
            result = true;
        }
        return result;
    }

    /**
     * Метод возвращает всех пользователей внутри хранилища.
     * @return все пользователи.
     */
    public List<User> findAll() {
        return STORE.findAll();
    }

    /**
     * Валидация адреса электронной почты, чтобы соответсвовала виду "sdsdsad@asdasd.asdasd".
     * @param email адрес электронной почты.
     * @return {@code true}, операция прошла успешно. {@code false}, произошла ошибка.
     */
    private boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("\\A[^@]+@([^@\\.]+\\.)+[^@\\.]+\\z");
        Matcher match = pattern.matcher(email);
        return match.matches();
    }
}
