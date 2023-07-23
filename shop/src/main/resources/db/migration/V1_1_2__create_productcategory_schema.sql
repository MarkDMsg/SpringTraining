CREATE TABLE product_category (
     id UUID NOT NULL,
     name VARCHAR(30) NOT NULL UNIQUE,
     description VARCHAR(30),
     PRIMARY KEY (id)
);