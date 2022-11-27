INSERT INTO products (id, price, title)
VALUES (1, 450.0, 'Cheese'),
       (2, 45.0, 'Wine'),
       (3, 65.0, 'Pine'),
       (4, 115.0, 'Cherry'),
       (5, 58.0, 'Wheat');
ALTER SEQUENCE product_seq RESTART WITH 6;
