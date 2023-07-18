CREATE TABLE IF NOT EXISTS order_detail (
    orders UUID REFERENCES orders(id),
    product UUID REFERENCES product(id),
    country VARCHAR(30),
    city VARCHAR(30),
    county VARCHAR(30),
    street_address VARCHAR(30),
    PRIMARY KEY (orders,product)
);