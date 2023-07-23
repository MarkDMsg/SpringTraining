CREATE TABLE stock (
     product_id UUID REFERENCES product(id),
     location_id UUID REFERENCES location(id),
     quantity INTEGER,
     PRIMARY KEY (product_id, location_id)
);