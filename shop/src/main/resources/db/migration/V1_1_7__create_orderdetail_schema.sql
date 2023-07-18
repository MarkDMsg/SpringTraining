CREATE TABLE OrderDetail (
     "order" UUID REFERENCES "order"(id),
     product UUID REFERENCES Product(id),
     address_country VARCHAR(30),
     address_city VARCHAR(30),
     address_county VARCHAR(30),
     address_streetaddress VARCHAR(30),
     PRIMARY KEY ("order",product)
);