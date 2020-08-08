create table body
(
    id   serial primary key,
    name VARCHAR(2000),
    CONSTRAINT uniq_body_name UNIQUE(name)
);


create table transmission
(
    id   serial primary key,
    gears int,
    CONSTRAINT uniq_transmission_gears UNIQUE (gears)
);

create table engine
(
    id   serial primary key,
    power INT,
    CONSTRAINT uniq_engine_power UNIQUE (power)
);

create table car (
     id   serial primary key,
     name varchar(200),
     engine_id int references engine (id),
     transmission_id int references transmission (id),
     body_id int references body (id)
);