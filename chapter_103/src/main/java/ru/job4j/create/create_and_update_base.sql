CREATE TABLE role(id serial PRIMARY KEY,
					role_name varchar(100));
CREATE TABLE users(id serial PRIMARY KEY, 
					username varchar(100),
					role_id INTEGER REFERENCES role(id));
CREATE TABLE rule(id serial PRIMARY KEY,
					rule_name varchar(100));
CREATE TABLE category(id serial PRIMARY KEY,
					category_name varchar(100));
CREATE TABLE status(id serial PRIMARY KEY,
					status_name varchar(100));
CREATE TABLE role_rule(id serial PRIMARY KEY,
					role_id INTEGER REFERENCES role(id),
					rules_id INTEGER REFERENCES rule(id));
CREATE TABLE item(id serial PRIMARY KEY,
					item_name varchar(100),
					user_id INTEGER UNIQUE REFERENCES users(id),
					category_id INTEGER REFERENCES category(id),
					status_id INTEGER REFERENCES status(id));
CREATE TABLE comment(id serial PRIMARY KEY,
					comment_text TEXT,
					item_id INTEGER REFERENCES item(id));
CREATE TABLE attach(id serial PRIMARY KEY,
					attach_data varchar(100),
					item_id INTEGER REFERENCES item(id));
						
INSERT INTO role(role_name) values ('Admin');
INSERT INTO role(role_name) values ('User');
INSERT INTO rule(rule_name) values ('Read');
INSERT INTO rule(rule_name) values ('Write');
INSERT INTO users(username, role_id) values ('Alexey', 1);
INSERT INTO users(username, role_id) values ('Alexandr', 2);
INSERT INTO category(category_name) values ('Important items');
INSERT INTO category(category_name) values ('Other');
INSERT INTO status(status_name) values ('Open');
INSERT INTO status(status_name) values ('Closed');
INSERT INTO role_rule(role_id, rules_id) values (1,1);
INSERT INTO role_rule(role_id, rules_id) values (1,2);
INSERT INTO role_rule(role_id, rules_id) values (2,1);
INSERT INTO item(item_name, user_id, category_id, status_id) values ('Help me!', 1, 1, 2);
INSERT INTO item(item_name, user_id, category_id, status_id) values ('Just talk', 2, 2, 1);
INSERT INTO comment(comment_text, item_id) values ('I have solved the problem.', 1);
INSERT INTO comment(comment_text, item_id) values ('Hello, how are you doing?', 2);
INSERT INTO attach(attach_data, item_id) values ('Image1.jpg', 1);
INSERT INTO attach(attach_data, item_id) values ('Image2.jpg', 2);