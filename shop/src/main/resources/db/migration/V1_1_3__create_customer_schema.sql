CREATE TABLE customer (
     id UUID NOT NULL,
     firstname VARCHAR(30) NOT NULL,
     lastname VARCHAR(30) NOT NULL,
     username VARCHAR(30) NOT NULL UNIQUE,
     password VARCHAR(30) NOT NULL,
     email_address VARCHAR(30) ,
     PRIMARY KEY (id)
);