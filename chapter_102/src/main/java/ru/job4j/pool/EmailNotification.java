package ru.job4j.pool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Класс оповещающий по почте всех людей из списка.
 * @author Alexey Makarov
 * @since 10.09.18
 * @version 0.1
 */
public class EmailNotification {
    private ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    /**
     * Метод создает задачу, которая составляет шаблон и добавляет задачу на выполнение.
     * @param user пользователь, которому отправляется письмо.
     */
    public void emailTo(User user) {
        pool.submit(() -> {
            StringBuilder sb = new StringBuilder();
            sb.append("subject = Notification ");
            sb.append(user.getUsername());
            sb.append(" to email ");
            sb.append(user.getEmail());
            sb.append(".\nbody = Add a new event to ");
            sb.append(user.getUsername());
            send(user.getUsername(), sb.toString(), user.getEmail());
        });
    }

    /**
     * Метод отправляет письмо пользователю, используя шаблон.
     * @param subject имя субъекта.
     * @param body тело письма.
     * @param email электронная почта, адрес.
     */
    public void send(String subject, String body, String email) {

    }

    /**
     * Класс - пользователь.
     * @author Alexey Makarov
     * @since 10.09.18
     * @version 0.1
     */
    public static class User {
        private String username;
        private String email;

        /**
         * Конструктор инициализирует поля имени пользователя и его почты.
         * @param username имя пользователя.
         * @param email почта.
         */
        public User(String username, String email) {
            this.username = username;
            this.email = email;
        }

        /**
         * Метод возвращает имя пользователя.
         * @return имя.
         */
        public String getUsername() {
            return username;
        }

        /**
         * Метод возвращает почту пользователя.
         * @return почтовый адрес.
         */
        public String getEmail() {
            return email;
        }
    }
}
