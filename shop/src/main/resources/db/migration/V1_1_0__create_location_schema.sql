CREATE TABLE location (
     id UUID NOT NULL,
     name VARCHAR(30) NOT NULL,
     country VARCHAR(30) NOT NULL,
     city VARCHAR(30) NOT NULL,
     county VARCHAR(30),
     street_address VARCHAR(30),
     PRIMARY KEY (id)
);