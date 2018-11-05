package ru.job4j.crud.service;

import ru.job4j.crud.repository.Store;
import ru.job4j.crud.model.User;
import ru.job4j.crud.repository.DBStore;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс логики, обрабатывает поступающую информацию, синглтон.
 * @author Alexey Makarov
 * @since 13.10.2018
 * @version 0.1
 */
public class ValidateService implements Validate {
    private static final Validate SINGLETON_INSTANCE = new ValidateService();
    private static final Store STORE = DBStore.getInstance();

    /**
     * Приватный конструктор для реализации синглтона.
     */
    private ValidateService() {

    }

    /**
     * Возвращает единственный объект этого класса.
     * @return объект.
     */
    public static Validate getInstance() {
        return SINGLETON_INSTANCE;
    }

    /**
     * Метод добавляет пользователя в хранилище.
     * @param name имя пользователя.
     * @param login логин.
     * @param email почта.
     * @return {@code true}, операция прошла успешно. {@code false}, произошла ошибка.
     */
    public boolean add(String name, String login, String password, String email, Boolean role) {
        boolean result = false;
        if (STORE.findByLogin(login) == null && !login.equals("")) {
            if (validateEmail(email)) {
                STORE.add(new User(name, login, password, email, role));
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
    public boolean update(int id, String name, String login, String password, String email, Boolean role) {
        boolean result = false;
        User user = STORE.findById(id);
        if (user != null) {
            if ((user.getLogin().equals(login) || STORE.findByLogin(login) == null)  && !login.equals("")) {
                if (validateEmail(email) || email == null) {
                    if (password.equals("")) {
                        STORE.update(id, new User(name, login, user.getPassword(), email, role));
                    } else {
                        STORE.update(id, new User(name, login, password, email, role));
                    }
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
    public Collection<User> findAll() {
        return STORE.findAll();
    }

    /**
     * Метод ищет пользователя в хранилище по идентификатору и возвращает его.
     * @param id идентификатор.
     * @return пользователь.
     */
    public User findById(int id) {
        return STORE.findById(id);
    }

    /**
     * Метод ищет пользователя в хранилище по логину и возвращает его.
     * @param login логин.
     * @return пользователь.
     */
    public User findByLogin(String login) {
        return STORE.findByLogin(login);
    }

    /**
     * Метод проверяет, есть ли у данного пользователя права администратора.
     * @param login пользователь.
     * @return {@code true} пользователь является администратором, {@code false} он обычный пользователь.
     */
    public boolean checkUserRole(String login) {
        User user = STORE.findByLogin(login);
        return user == null ? false : user.getRole();
    }

    /**
     * Метод ищет пользователя с данным логином и паролем для аутентификации.
     * @param login логин.
     * @param password пароль.
     * @return {@code true}, если логин и пароль верные. {@code false}, если нет.
     */
    public boolean signIn(String login, String password) {
        boolean result = false;
        for (User user : this.findAll()) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                result = true;
                break;
            }
        }
        return result;
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
