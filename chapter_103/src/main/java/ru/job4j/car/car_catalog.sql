--Создание базы.
CREATE DATABASE car_catalog;

--Создание таблиц.
CREATE TABLE engine(
	id SERIAL PRIMARY KEY,
	name VARCHAR(100)
);
CREATE TABLE transmission(
	id SERIAL PRIMARY KEY,
	name VARCHAR(100)
);
CREATE TABLE car_body(
	id SERIAL PRIMARY KEY,
	name VARCHAR(100)
);
CREATE TABLE car(
	id SERIAL PRIMARY KEY,
	name VARCHAR(100),
	engine_id INTEGER REFERENCES engine(id),
	transmission_id INTEGER REFERENCES transmission(id),
	car_body_id INTEGER REFERENCES car_body(id)
);

--Заполнение таблиц.
INSERT INTO engine(name) VALUES ('Engine one');
INSERT INTO engine(name) VALUES ('Engine two');
INSERT INTO engine(name) VALUES ('Engine three');
INSERT INTO engine(name) VALUES ('Engine four');
INSERT INTO engine(name) VALUES ('Engine five');
INSERT INTO car_body(name) VALUES ('Car_body one');
INSERT INTO car_body(name) VALUES ('Car_body two');
INSERT INTO car_body(name) VALUES ('Car_body three');
INSERT INTO car_body(name) VALUES ('Car_body four');
INSERT INTO car_body(name) VALUES ('Car_body five');
INSERT INTO transmission(name) VALUES ('Transmission one');
INSERT INTO transmission(name) VALUES ('Transmission two');
INSERT INTO transmission(name) VALUES ('Transmission three');
INSERT INTO transmission(name) VALUES ('Transmission four');
INSERT INTO transmission(name) VALUES ('Transmission five');
INSERT INTO car(name, engine_id, transmission_id, car_body_id) VALUES ('Тачка обычная', 1, 2, 3);
INSERT INTO car(name, engine_id, transmission_id, car_body_id) VALUES ('Тачка необычная', 3, 1, 5);
INSERT INTO car(name, engine_id, transmission_id, car_body_id) VALUES ('Тачка дорогая', 4, 4, 1);
INSERT INTO car(name, engine_id, transmission_id, car_body_id) VALUES (
	'Тачка черная',
	(SELECT id FROM engine WHERE name = 'Engine four'),
	(SELECT id FROM transmission WHERE name = 'Transmission three'),
	(SELECT id FROM car_body WHERE name = 'Car_body five')
);

--1. Список всех машин и все привязанные к ним детали.
SELECT c.name, e.name, t.name, b.name FROM car AS c
JOIN engine AS e ON c.engine_id = e.id
JOIN transmission AS t ON c.transmission_id = t.id
JOIN car_body AS b ON c.car_body_id = b.id;

--2. Список двигателей, которые не используются в машинах.
SELECT e.name FROM engine AS e LEFT OUTER JOIN car AS c ON c.engine_id = e.id WHERE c.id IS NULL;

--3. Список коробок передач, которые не используются в машинах.
SELECT t.name FROM transmission AS t LEFT OUTER JOIN car AS c ON c.transmission_id = t.id WHERE c.id IS NULL;

--4. Список корпусов, которые не используются в машинах.
SELECT b.name FROM car_body AS b LEFT OUTER JOIN car AS c ON c.car_body_id = b.id WHERE c.id IS NULL;