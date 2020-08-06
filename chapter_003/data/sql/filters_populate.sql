CREATE TABLE type
(
    id   serial primary key,
    name VARCHAR(2000)
);

CREATE TABLE product
(
    id           serial primary key,
    name         VARCHAR(2000),
    expired_date date,
    price        int,
    type_id      int references type (id)
);

INSERT INTO type (name)
VALUES ('СЫР');
INSERT INTO type (name)
VALUES ('МОЛОКО');
INSERT INTO type (name)
VALUES ('КОЛБАСА');
INSERT INTO type (name)
VALUES ('ПИВО');
INSERT INTO type (name)
VALUES ('ВОДКА');
INSERT INTO type (name)
VALUES ('БАЛАЛАЙКА');


INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Молодость' , '2020-08-07', 100, (SELECT id FROM type WHERE name = 'СЫР'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Старость' , '2020-08-08', 110, (SELECT id FROM type WHERE name = 'СЫР'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Вечность' , '2020-08-09', 120, (SELECT id FROM type WHERE name = 'СЫР'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Пираты' , '2020-08-10', 130, (SELECT id FROM type WHERE name = 'МОЛОКО'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Крокодилы' , '2020-08-10', 140, (SELECT id FROM type WHERE name = 'МОЛОКО'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Вобла' , '2020-08-10', 150, (SELECT id FROM type WHERE name = 'МОЛОКО'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Собачья' , '2020-08-11', 160, (SELECT id FROM type WHERE name = 'КОЛБАСА'));


INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Молодость' , '2020-08-07', 100, (SELECT id FROM type WHERE name = 'СЫР'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Старость' , '2020-08-08', 110, (SELECT id FROM type WHERE name = 'СЫР'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Вечность' , '2020-08-09', 120, (SELECT id FROM type WHERE name = 'СЫР'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Пираты' , '2020-08-10', 130, (SELECT id FROM type WHERE name = 'МОЛОКО'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Крокодилы' , '2020-08-10', 140, (SELECT id FROM type WHERE name = 'МОЛОКО'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Вобла' , '2020-08-10', 150, (SELECT id FROM type WHERE name = 'МОЛОКО'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Собачья' , '2020-08-11', 160, (SELECT id FROM type WHERE name = 'КОЛБАСА'));
INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Молодость' , '2020-08-07', 100, (SELECT id FROM type WHERE name = 'СЫР'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Старость' , '2020-08-08', 110, (SELECT id FROM type WHERE name = 'СЫР'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Вечность' , '2020-08-09', 120, (SELECT id FROM type WHERE name = 'СЫР'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Пираты' , '2020-08-10', 130, (SELECT id FROM type WHERE name = 'МОЛОКО'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Крокодилы' , '2020-08-10', 140, (SELECT id FROM type WHERE name = 'МОЛОКО'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Вобла' , '2020-08-10', 150, (SELECT id FROM type WHERE name = 'МОЛОКО'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Собачья' , '2020-08-11', 160, (SELECT id FROM type WHERE name = 'КОЛБАСА'));
INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Молодость' , '2020-08-07', 100, (SELECT id FROM type WHERE name = 'СЫР'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Старость' , '2020-08-08', 110, (SELECT id FROM type WHERE name = 'СЫР'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Вечность' , '2020-08-09', 120, (SELECT id FROM type WHERE name = 'СЫР'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Пираты' , '2020-08-10', 130, (SELECT id FROM type WHERE name = 'МОЛОКО'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Крокодилы' , '2020-08-10', 140, (SELECT id FROM type WHERE name = 'МОЛОКО'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Вобла' , '2020-08-10', 150, (SELECT id FROM type WHERE name = 'МОЛОКО'));

INSERT INTO product (name, expired_date, price, type_id)
VALUES ('Собачья' , '2020-08-11', 160, (SELECT id FROM type WHERE name = 'КОЛБАСА'));



