INSERT INTO products (id, price, title)
VALUES (1, 450.0, 'Cheese'),
       (2, 450.0, 'Wine'),
       (3, 450.0, 'Pine'),
       (4, 450.0, 'Cherry'),
       (5, 450.0, 'Wheat');
ALTER SEQUENCE product_seq RESTART WITH 6;
