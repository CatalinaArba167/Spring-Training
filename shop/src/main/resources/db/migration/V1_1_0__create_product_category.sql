CREATE TABLE IF NOT EXISTS product_category(
    id UUID NOT NULL PRIMARY KEY,
    name VARCHAR(20),
    description VARCHAR(128)
);