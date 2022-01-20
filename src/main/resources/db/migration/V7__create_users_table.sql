CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    surname VARCHAR(150),
    name VARCHAR(150),
    patronymic VARCHAR(150),
    phone VARCHAR(150),
    email VARCHAR(150),
    login VARCHAR(1024),
    password VARCHAR(1024)
);