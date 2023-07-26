CREATE TABLE IF NOT EXISTS orders (
    id UUID NOT NULL PRIMARY KEY,
    customer UUID REFERENCES customer(id),
    created_at TIMESTAMP,
    country VARCHAR(50),
    city VARCHAR(50),
    county VARCHAR(50),
    street_address VARCHAR(50)
);