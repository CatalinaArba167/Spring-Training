CREATE TABLE IF NOT EXISTS stock (
    product UUID REFERENCES product(id),
    location UUID REFERENCES location(id),
    quantity INTEGER,
    PRIMARY KEY (product,location)
);