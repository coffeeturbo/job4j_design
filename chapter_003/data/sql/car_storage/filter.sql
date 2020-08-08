/**     Вывести список всех машин и все привязанные к ним детали    **/

SELECT
       car.name as car_name,
       b.name   as body_name,
       e.power  as power,
       t.gears  as gears
FROM car
         INNER JOIN body b on car.body_id = b.id
         INNER JOIN engine e on car.engine_id = e.id
         INNER JOIN transmission t on car.transmission_id = t.id

/**     Вывести список всех машин и все привязанные к ним детали    **/

/** Кузов **/
SELECT
       b.name   as body_name,
       b.id
FROM body b
    LEFT JOIN car c on b.id = c.body_id
WHERE
      c.name IS NULL

/** Двигатель **/
SELECT
   e.power,
   e.id
FROM engine e
         LEFT JOIN car c on e.id = c.engine_id
WHERE
    c.name IS NULL

/** Трансмиссия **/
SELECT
    t.id,
    t.gears
FROM transmission t
         LEFT JOIN car c on t.id = c.transmission_id
WHERE
    c.name IS NULL