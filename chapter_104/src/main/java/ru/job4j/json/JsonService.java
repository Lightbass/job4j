package ru.job4j.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ConcurrentHashMap;

public class JsonService {
    private static final Logger LOGGER = LogManager.getLogger(JsonService.class);

    private static final JsonService SINGLETON_INSTANCE = new JsonService();
    private final ConcurrentHashMap<String, User> map = new ConcurrentHashMap<>();
    private final ObjectMapper mapper = new ObjectMapper();

    {
        User user1 = new User("Александр", "Папанага", "deddok@guddok.ru", "password", "Мужской", "Холостяк, никому не нужен");
        User user2 = new User("Иванов", "Артем", "tema666@gmail.com", "password", "Мужской", "Геймер");
        User user3 = new User("Ольга", "Яковлева", "olga222@mail.ru", "password", "Женский", "Живу с кошками.");
        map.put(user1.getEmail(), user1);
        map.put(user2.getEmail(), user2);
        map.put(user3.getEmail(), user3);
    }

    public static JsonService getInstance() {
        return SINGLETON_INSTANCE;
    }

    public void parseJson(InputStream in) {
        try {
            User user = mapper.readValue(in, User.class);
            map.put(user.getEmail(), user);
        } catch (IOException ioe) {
            LOGGER.error(ioe.getMessage(), ioe);
        }
    }

    public void encodeJson(OutputStream out) {
        try {
            mapper.writeValue(out, map.values());
        } catch (IOException ioe) {
            LOGGER.error(ioe.getMessage(), ioe);
        }
    }

    public User getUserByEmail(String email) {
        return map.get(email);
    }
}
