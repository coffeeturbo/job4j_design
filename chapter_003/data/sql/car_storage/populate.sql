INSERT INTO body(name) VALUES ('седан');
INSERT INTO body(name) VALUES ('хэтчбэк');
INSERT INTO body(name) VALUES ('универсал');
INSERT INTO body(name) VALUES ('кроссовер');
INSERT INTO body(name) VALUES ('жип');

INSERT INTO transmission(gears) VALUES (4);
INSERT INTO transmission(gears) VALUES (5);
INSERT INTO transmission(gears) VALUES (6);
INSERT INTO transmission(gears) VALUES (7);
INSERT INTO transmission(gears) VALUES (8);

INSERT INTO engine(power) VALUES (80);
INSERT INTO engine(power) VALUES (90);
INSERT INTO engine(power) VALUES (100);
INSERT INTO engine(power) VALUES (150);
INSERT INTO engine(power) VALUES (180);
INSERT INTO engine(power) VALUES (190);
INSERT INTO engine(power) VALUES (200);
INSERT INTO engine(power) VALUES (250);

INSERT INTO car(name, engine_id, transmission_id, body_id)
VALUES (
           'bmw x3',
           (SELECT id FROM engine WHERE power = 190),
           (SELECT id FROM transmission WHERE gears = 8),
           (SELECT id FROM body WHERE name = 'кроссовер')
       );

INSERT INTO car(name, engine_id, transmission_id, body_id)
VALUES (
           'ford focus',
           (SELECT id FROM engine WHERE power = 100),
           (SELECT id FROM transmission WHERE gears = 6),
           (SELECT id FROM body WHERE name = 'седан')
       );

INSERT INTO car(name, engine_id, transmission_id, body_id)
VALUES (
           'ford focus',
           (SELECT id FROM engine WHERE power = 100),
           (SELECT id FROM transmission WHERE gears = 6),
           (SELECT id FROM body WHERE name = 'универсал')
       );

INSERT INTO car(name, engine_id, transmission_id, body_id)
VALUES (
           'ford focus',
           (SELECT id FROM engine WHERE power = 100),
           (SELECT id FROM transmission WHERE gears = 6),
           (SELECT id FROM body WHERE name = 'хэтчбэк')
       );

INSERT INTO car(name, engine_id, transmission_id, body_id)
VALUES (
           'ford focus',
           (SELECT id FROM engine WHERE power = 150),
           (SELECT id FROM transmission WHERE gears = 6),
           (SELECT id FROM body WHERE name = 'седан')
       );

INSERT INTO car(name, engine_id, transmission_id, body_id)
VALUES (
           'ford focus',
           (SELECT id FROM engine WHERE power = 150),
           (SELECT id FROM transmission WHERE gears = 6),
           (SELECT id FROM body WHERE name = 'универсал')
       );

INSERT INTO car(name, engine_id, transmission_id, body_id)
VALUES (
           'ford focus',
           (SELECT id FROM engine WHERE power = 150),
           (SELECT id FROM transmission WHERE gears = 6),
           (SELECT id FROM body WHERE name = 'хэтчбэк')
       );

INSERT INTO car(name, engine_id, transmission_id, body_id)
VALUES (
           'volkswagen jetta',
           (SELECT id FROM engine WHERE power = 100),
           (SELECT id FROM transmission WHERE gears = 6),
           (SELECT id FROM body WHERE name = 'седан')
       );

INSERT INTO car(name, engine_id, transmission_id, body_id)
VALUES (
           'volkswagen jetta',
           (SELECT id FROM engine WHERE power = 100),
           (SELECT id FROM transmission WHERE gears = 6),
           (SELECT id FROM body WHERE name = 'универсал')
       );

INSERT INTO car(name, engine_id, transmission_id, body_id)
VALUES (
           'volkswagen jetta',
           (SELECT id FROM engine WHERE power = 100),
           (SELECT id FROM transmission WHERE gears = 6),
           (SELECT id FROM body WHERE name = 'хэтчбэк')
       );

INSERT INTO car(name, engine_id, transmission_id, body_id)
VALUES (
           'volkswagen jetta',
           (SELECT id FROM engine WHERE power = 150),
           (SELECT id FROM transmission WHERE gears = 6),
           (SELECT id FROM body WHERE name = 'седан')
       );

INSERT INTO car(name, engine_id, transmission_id, body_id)
VALUES (
           'volkswagen jetta',
           (SELECT id FROM engine WHERE power = 150),
           (SELECT id FROM transmission WHERE gears = 6),
           (SELECT id FROM body WHERE name = 'универсал')
       );

INSERT INTO car(name, engine_id, transmission_id, body_id)
VALUES (
           'volkswagen jetta',
           (SELECT id FROM engine WHERE power = 150),
           (SELECT id FROM transmission WHERE gears = 6),
           (SELECT id FROM body WHERE name = 'хэтчбэк')
       );