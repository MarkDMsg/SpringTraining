CREATE TABLE order_detail (
     product_order_id UUID REFERENCES product_order(id),
     product_id UUID REFERENCES product(id),
     shipped_from UUID REFERENCES location(id),
     quantity INTEGER NOT NULL,
     PRIMARY KEY (product_order_id,product_id)
);