package ru.job4j.crud;

import net.jcip.annotations.ThreadSafe;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;

/**
 * Класс - реализация хранилища пользователей, синглтон.
 * @author Alexey Makarov
 * @since 13.10.2018
 * @version 0.1
 */
@ThreadSafe
public class MemoryStore implements Store {
    private static final MemoryStore SINGLETON_INSTANCE = new MemoryStore();
    private final ValidateService logic = ValidateService.getInstance();
    private final Map<Integer, User> users = new ConcurrentHashMap<>();

    /**
     * Приватный конструктор для реализации синглтона.
     */
    private MemoryStore() {

    }

    /**
     * Возвращает единственный объект этого класса.
     * @return объект.
     */
    public static MemoryStore getInstance() {
        return SINGLETON_INSTANCE;
    }

    /**
     * Метод добавляет пользователя в хранилище.
     * @param user добавляемый пользователь.
     */
    @Override
    public void add(User user) {
        users.put(user.getId(), user);
    }

    /**
     * Метод обновляет пользователя в хранилище.
     * @param id идентификатор обновляемого пользователя.
     * @param user пользователь, который встанет на его место.
     */
    @Override
    public void update(int id, User user) {
        user.setId(id);
        users.put(id, user);
    }

    /**
     * Метод удаляет пользователя из хранилища.
     * @param id идентификатор удаляемого пользователя.
     */
    @Override
    public void delete(int id) {
        users.remove(id);
    }

    /**
     * Метод возвращает всех пользователей внутри хранилища.
     * @return все пользователи.
     */
    @Override
    public Collection<User> findAll() {
        return users.values();
    }

    /**
     * Метод ищет пользователя в хранилище по идентификатору и возвращает его.
     * @param id идентификатор.
     * @return пользователь.
     */
    @Override
    public User findById(int id) {
        return users.getOrDefault(id, null);
    }

    /**
     * Метод ищет пользователя в хранилище по логину и возвращает его.
     * @param login логин.
     * @return пользователь.
     */
    @Override
    public User findByLogin(String login) {
        User result = null;
        for (User user : users.values()) {
            if (user.getLogin().equals(login)) {
                result = user;
                break;
            }
        }
        return result;
    }
}
