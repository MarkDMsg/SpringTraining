CREATE TABLE OrderDetail (
     "order" UUID REFERENCES "order"(id),
     product UUID REFERENCES Product(id),
     shippedfrom UUID REFERENCES Location(id),
     quantity INTEGER,
     PRIMARY KEY ("order",product)
);