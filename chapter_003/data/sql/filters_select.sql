/**   Написать запрос получение всех продуктов с типом "СЫР"   */

SELECT type.name, product.*
FROM product, type
WHERE product.type_id = type.id AND type.name = 'СЫР';

/**    Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"   */

SELECT product.*
FROM product
WHERE product.name LIKE '%мороженное%';

/**    Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце  */

SELECT product.*
FROM product
WHERE product.expired_date > '2020-08-31';

/**    Написать запрос, который выводит самый дорогой продукт.  */

SELECT product.*
FROM product
WHERE product.price >= (SELECT max(product.price) FROM product);

/**    Написать запрос, который выводит количество всех продуктов определенного типа  */

SELECT type.name, count(*)
FROM product , type
WHERE product.type_id = type.id
GROUP BY type.name

/**    Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"  */

SELECT product.*, type.name
FROM product , type
WHERE product.type_id = type.id AND (type.name = 'СЫР' OR type.name = 'МОЛОКО')

/**    Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.  */

SELECT product.name, count(*)
FROM product
GROUP BY product.name
HAVING count(*) > 10

/**    Вывести все продукты и их тип.  */

SELECT product.*, type.name
FROM product , type
WHERE product.type_id = type.id