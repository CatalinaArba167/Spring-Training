CREATE TABLE IF NOT EXISTS location (
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(30),
    country VARCHAR(30),
    city VARCHAR(30),
    county VARCHAR(30),
    address VARCHAR(30)
);