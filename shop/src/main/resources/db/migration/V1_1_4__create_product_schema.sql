CREATE TABLE product (
     id UUID NOT NULL,
     name VARCHAR(30) NOT NULL,
     description VARCHAR(30),
     price NUMERIC,
     weight FLOAT8,
     category_id UUID REFERENCES product_category(id),
     supplier VARCHAR(30) NOT NULL,
     image_url VARCHAR(30),
     PRIMARY KEY (id)
);