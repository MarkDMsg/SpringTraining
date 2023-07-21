CREATE TABLE Product (
     id UUID NOT NULL,
     name VARCHAR(30),
     description VARCHAR(30),
     price numeric,
     weight float8,
     category UUID REFERENCES ProductCategory(id),
     supplier VARCHAR(30),
     imageurl VARCHAR(30),
     PRIMARY KEY (id)
);