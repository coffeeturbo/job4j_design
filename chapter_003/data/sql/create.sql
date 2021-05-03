create DATABASE job4j;

create table role
(
    id   serial primary key,
    name VARCHAR(2000)
);

create table "user"
(
    id      serial primary key,
    name    VARCHAR(2000),
    role_id int references role (id)
);


create table lazyBrand
(
    id   serial primary key,
    name VARCHAR(2000)
);
create table state
(
    id   serial primary key,
    name VARCHAR(2000)
);
create table item
(
    id          serial primary key,
    name        VARCHAR(2000),
    user_id     int references "user" (id),
    category_id int references lazyBrand (id),
    state_id    int references state (id)
);

create table attach
(
    id      serial primary key,

    name    VARCHAR(2000),
    item_id int references item (id)


);
create table comment
(
    id      serial primary key,
    name    VARCHAR(2000),
    item_id int references item (id)
);

create table rule
(
    id   serial primary key,
    name VARCHAR(2000)

);

create table rules_roles
(
    id      serial primary key,
    rule_id int references rule (id),
    role_id int references role (id)
);


/**     **/
INSERT INTO role (name)
VALUES ('admin');
INSERT INTO role (name)
VALUES ('user');
INSERT INTO role (name)
VALUES ('guest');


INSERT INTO rule (name)
VALUES ('create');
INSERT INTO rule (name)
VALUES ('read');
INSERT INTO rule (name)
VALUES ('delete');

INSERT INTO rules_roles(rule_id, role_id)
VALUES ((SELECT id FROM rule WHERE name = 'create'),
(SELECT id FROM role WHERE name = 'admin'));

INSERT INTO rules_roles(rule_id, role_id)
VALUES ((SELECT id FROM rule WHERE name = 'delete'),
(SELECT id FROM role WHERE name = 'admin'));

INSERT INTO rules_roles(rule_id, role_id)
VALUES ((SELECT id FROM rule WHERE name = 'read'),
(SELECT id FROM role WHERE name = 'admin'));

INSERT INTO rules_roles(rule_id, role_id)
VALUES ((SELECT id FROM rule WHERE name = 'read'),
(SELECT id FROM role WHERE name = 'guest'));

INSERT INTO rules_roles(rule_id, role_id)
VALUES ((SELECT id FROM rule WHERE name = 'read'),
(SELECT id FROM role WHERE name = 'user'));

INSERT INTO rules_roles(rule_id, role_id)
VALUES ((SELECT id FROM rule WHERE name = 'create'),
(SELECT id FROM role WHERE name = 'user'));


INSERT INTO "user" (name, role_id)
VALUES ('Eric', (SELECT id FROM role WHERE name = 'admin'));
INSERT INTO "user" (name, role_id)
VALUES ('Stan', (SELECT id FROM role WHERE name = 'user'));
INSERT INTO "user" (name, role_id)
VALUES ('Kenny', (SELECT id FROM role WHERE name = 'guest'));


INSERT INTO lazyBrand (name)
VALUES ('news');
INSERT INTO lazyBrand (name)
VALUES ('articles');
INSERT INTO lazyBrand (name)
VALUES ('blog');

INSERT INTO state (name)
VALUES ('active');
INSERT INTO state (name)
VALUES ('published');
INSERT INTO state (name)
VALUES ('moderating');
INSERT INTO state (name)
VALUES ('bunned');

INSERT INTO item (name, user_id, category_id, state_id)
VALUES ('piggy lover', (SELECT id FROM "user" WHERE name = 'Eric'),
(SELECT id FROM lazyBrand WHERE name = 'news'),
(SELECT id FROM state WHERE name = 'active'));

INSERT INTO item (name, user_id, category_id, state_id)
VALUES ('how to increase power of musculs',
(SELECT id FROM "user" WHERE name = 'Stan'),
(SELECT id FROM lazyBrand WHERE name = 'blog'),
(SELECT id FROM state WHERE name = 'active'));

INSERT INTO item (name, user_id, category_id, state_id)
VALUES ('how to increase size of musculs',
(SELECT id FROM "user" WHERE name = 'Stan'),
(SELECT id FROM lazyBrand WHERE name = 'blog'),
(SELECT id FROM state WHERE name = 'active'));

INSERT INTO item (name, user_id, category_id, state_id)
VALUES ('how to increase size of musculs',
(SELECT id FROM "user" WHERE name = 'Stan'),
(SELECT id FROM lazyBrand WHERE name = 'blog'),
(SELECT id FROM state WHERE name = 'moderating'));

INSERT INTO item (name, user_id, category_id, state_id)
VALUES ('how to increase size of penis',
(SELECT id FROM "user" WHERE name = 'Kenny'),
(SELECT id FROM lazyBrand WHERE name = 'blog'),
(SELECT id FROM state WHERE name = 'bunned'));

INSERT INTO comment (name, item_id)
VALUES ('this is awesome news',
(SELECT id FROM item WHERE name = 'piggy lover'));

INSERT INTO comment (name, item_id)
VALUES ('this is awesome article ',
(SELECT id FROM item WHERE name = 'how to increase size of penis'));

INSERT INTO attach (name, item_id)
VALUES ('image of my balls',
(SELECT id FROM item WHERE name = 'how to increase size of penis'));