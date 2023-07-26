CREATE TABLE IF NOT EXISTS order_detail (
    orders UUID REFERENCES orders(id),
    product UUID REFERENCES product(id),
    shipped_from UUID REFERENCES location(id),
    quantity INTEGER,
    PRIMARY KEY (orders,product)
);