--Создание базы
CREATE DATABASE stock;

--Создание используемых таблиц
CREATE TABLE type(
	id SERIAL PRIMARY KEY,
	name VARCHAR(100)
);
CREATE TABLE product(
	id SERIAL PRIMARY KEY,
	name VARCHAR(100),
	type_id INTEGER REFERENCES type(id),
	expired_date DATE,
	price DEC(5,2),
	amount INTEGER
);

--Заполнение таблицы
INSERT INTO type(name) values ('СЫР');
INSERT INTO type(name) values ('Молоко');
INSERT INTO type(name) values ('Мороженое');
INSERT INTO product(name, type_id, expired_date, price, amount) values ('Сыр обычный', 1, '2019-09-26', 50.5, 1);
INSERT INTO product(name, type_id, expired_date, price, amount) values ('Сыр с плесенью', 1, '2019-10-30', 99.99, 2);
INSERT INTO product(name, type_id, expired_date, price, amount) values ('Сыр крутой', 1, '2018-11-30', 200, 3);
INSERT INTO product(name, type_id, expired_date, price, amount) values ('Молоко обычное', 2, '2018-10-26', 20.25, 2);
INSERT INTO product(name, type_id, expired_date, price, amount) values ('Молоко 3%', 2, '2018-10-30', 50.15, 4);
INSERT INTO product(name, type_id, expired_date, price, amount) values ('Молоко простоквашино', 2, '2018-11-05', 30.65, 4);
INSERT INTO product(name, type_id, expired_date, price, amount) values ('Молоко фиговое', 2, '2018-09-30', 10.25, 4);
INSERT INTO product(name, type_id, expired_date, price, amount) values ('Мороженое обычное', 3, '2018-11-25', 40.1, 2);
INSERT INTO product(name, type_id, expired_date, price, amount) values ('Мороженое большое', 3, '2018-10-15', 65.2, 3);
INSERT INTO product(name, type_id, expired_date, price, amount) values ('Мороженое дорогое', 3, '2018-10-05', 100.23, 5);

--1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT * FROM product AS p WHERE p.type_id=1;

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"
SELECT * FROM product AS p WHERE p.name LIKE '%Мороженое%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT * FROM product AS p WHERE DATE_PART('month',p.expired_date) = DATE_PART('month',LOCALTIMESTAMP) + 1 
AND DATE_PART('year', LOCALTIMESTAMP) = DATE_PART('year', p.expired_date);
--SELECT * FROM product AS p WHERE p.expired_date BETWEEN '2018-10-01' AND '2018-10-31';

--4. Написать запрос, который выводит самый дорогой продукт.
SELECT * FROM product AS p WHERE p.price = (SELECT MAX(price) FROM product);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT COUNT(p.id) FROM product AS p WHERE p.type_id = 2;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT * FROM product AS p WHERE p.type_id = 1 OR p.type_id = 2;

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.  
SELECT * FROM type AS t WHERE (SELECT SUM(p.amount) FROM product AS p WHERE type_id = t.id) < 10;

--8. Вывести все продукты и их тип.
SELECT p.name, p.price, p.expired_date, t.name FROM product AS p INNER JOIN type AS t on p.type_id = t.id;