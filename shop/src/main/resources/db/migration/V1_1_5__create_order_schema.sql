CREATE TABLE "order"(
     id UUID NOT NULL,
     customer_id UUID REFERENCES customer(id),
     created_at TIMESTAMP,
     country VARCHAR(30),
     city VARCHAR(30),
     county VARCHAR(30),
     street_address VARCHAR(30),
     PRIMARY KEY (id)
);