INSERT INTO users (id, archive, email, name, password, role)
VALUES (1, false, 'mail@mail.ru', 'admin', '$2a$10$WNiqLpqOpQBk1yNkE9XTF.JMr43vQ/KKuYIV6f5NaZxZBIH25I3kO', 'ADMIN');

ALTER SEQUENCE user_seq RESTART WITH 2;
