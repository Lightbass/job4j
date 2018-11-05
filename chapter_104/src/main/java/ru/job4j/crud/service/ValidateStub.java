package ru.job4j.crud.service;

import ru.job4j.crud.model.User;
import ru.job4j.crud.repository.MemoryStore;
import ru.job4j.crud.repository.Store;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateStub implements Validate {
    public static final Store STORE = MemoryStore.getInstance();
    private int ids = 0;

    public ValidateStub() {
        STORE.findAll().clear();
        STORE.add(new User("Vasya", "vas", "vas", "vas@vas.com", true));
        STORE.add(new User("Masya", "mas", "mas", "mas@mas.com", false));
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
