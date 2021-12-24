INSERT INTO car (id, model, price)
VALUES (-1, 'BMW X5', 1000000),
       (-2, 'BMW X6', 2000000),
       (-3, 'BMW X7', 3000000);

INSERT INTO customer (id, name, phone_number)
VALUES (-1, 'Иванов Сергей', '+79107891122'),
       (-2, 'Коробкин Олег', '+79107891155'),
       (-3, 'Олейкин Роман', '+79107891166');

INSERT INTO orders (id, amount, instant, customer_id, quantity) VALUES (-1, 1000000, '20211001', -1, 1);
INSERT INTO orders_car (orders_id, car_id) VALUES (-1, -1);

INSERT INTO orders (id, amount, instant, customer_id, quantity) VALUES (-2, 4000000, '20211002', -2, 2);
INSERT INTO orders_car (orders_id, car_id) VALUES (-2, -2);

INSERT INTO orders (id, amount, instant, customer_id, quantity) VALUES (-6, 2000000, '20211002', -2, 1);
INSERT INTO orders_car (orders_id, car_id) VALUES (-6, -1);

INSERT INTO orders (id, amount, instant, customer_id, quantity) VALUES (-3, 3000000, '20211002', -3, 1);
INSERT INTO orders_car (orders_id, car_id) VALUES (-3, -3);

INSERT INTO orders (id, amount, instant, customer_id, quantity) VALUES (-4, 1000000, '20211003', -2, 1);
INSERT INTO orders_car (orders_id, car_id) VALUES (-4, -1);

INSERT INTO orders (id, amount, instant, customer_id, quantity) VALUES (-5, 1000000, '20211004', -3, 1);
INSERT INTO orders_car (orders_id, car_id) VALUES (-5, -1);