CREATE TABLE company (
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person (
    id integer NOT NULL,
    name character varying,
    company_id integer,
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company(id, name) VALUES (1, 'Apple');
INSERT INTO company(id, name) VALUES (2, 'Google');
INSERT INTO company(id, name) VALUES (3, 'Microsoft');
INSERT INTO company(id, name) VALUES (4, 'Blizzard');
INSERT INTO company(id, name) VALUES (5, 'BMW');

INSERT INTO person(id, name, company_id) VALUES (1, 'John', 1);
INSERT INTO person(id, name, company_id) VALUES (2, 'Ivan', 2);
INSERT INTO person(id, name, company_id) VALUES (3, 'Sergio', 3);
INSERT INTO person(id, name, company_id) VALUES (4, 'Hjui', 4);
INSERT INTO person(id, name, company_id) VALUES (5, 'Frenchi', 5);
INSERT INTO person(id, name, company_id) VALUES (6, 'Butcher', 1);
INSERT INTO person(id, name, company_id) VALUES (7, 'Homelander', 2);
INSERT INTO person(id, name, company_id) VALUES (8, 'Mave', 3);

-- names of all persons that are NOT in the company with id = 5
SELECT person.name, c.name
FROM person
JOIN company c on person.company_id = c.id
WHERE company_id != 5;

-- company name for each person
SELECT person.name, c.name
FROM person
JOIN company c on person.company_id = c.id;

--  Select the name of the company with the maximum number of persons + number of persons in this company
SELECT count(c.name) total, c.name
FROM person
JOIN company c on person.company_id = c.id
GROUP BY c.id
ORDER BY count(c.name) DESC
LIMIT 1;
