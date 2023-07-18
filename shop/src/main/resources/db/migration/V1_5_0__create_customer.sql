CREATE TABLE IF NOT EXISTS customer (
    id UUID NOT NULL PRIMARY KEY ,
    first_name VARCHAR(20),
    last_name VARCHAR(20),
    username VARCHAR(20),
    password VARCHAR(20),
    email_address VARCHAR(30)
);