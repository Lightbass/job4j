package ru.job4j.mod;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для сравнения коллекций.
 * @author Alexey Makarov
 * @since 27.08.18
 * @version 0.1
 */
class Store {

    /**
     * Метод возвращает объект данных о разности между хранилищами.
     * @param current хранилище с котором
     * @return информация об изменениях.
     */
    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        if (current.isEmpty()) {
            info.deleted = previous.size();
        } else {
                int n = 0;
                for (User user : previous) {
                    if (current.get(n).id == user.id) {
                        if (!user.name.equals(current.get(n).name)) {
                            info.modified++;
                        }
                    } else {
                        info.deleted++;
                    }
                    n++;
                }
                info.created = current.size() - previous.size() + info.deleted;
        }
        return info;
    }

    /**
     * Класс - пользователь. С полями идентификатора и имени.
     */
    public static class User {
        private int id;
        private String name;

        /**
         * Конструктор инициализирует поля идентификатора и имени.
         * @param id идентификатор.
         * @param name имя.
         */
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        /**
         * Метод возвращает идентификатор пользователя.
         * @return идентификатор.
         */
        public int getId() {
            return id;
        }

        /**
         * Метод возвращает имя пользователя.
         * @return имя.
         */
        public String getName() {
            return name;
        }
    }

    /**
     * Информация об изменениях в коллекции.
     */
    public static class Info {
        private int created;
        private int deleted;
        private int modified;

        /**
         * Метод возвращает кол-во новых элементов в коллекции.
         * @return кол-во созданных элементов.
         */
        public int getCreated() {
            return created;
        }

        /**
         * Метод возвращает кол-во удаленных элементов в коллекции.
         * @return кол-во удаленных элементов.
         */
        public int getDeleted() {
            return deleted;
        }

        /**
         * Метод возвращает кол-во измененных элементов в коллекции.
         * @return кол-во измененных элементов в коллекции.
         */
        public int getModified() {
            return modified;
        }
    }
}