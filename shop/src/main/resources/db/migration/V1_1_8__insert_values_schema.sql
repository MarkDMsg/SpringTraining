

INSERT INTO location (id, name, country, city, county, street_address)
VALUES (
    'f47ac10b-58cc-4372-a567-0e02b2c3d479',
    'Sample Location',
    'United States',
    'New York',
     NULL,
    '123 Main St'
),(
      '6d5d413b-56e5-4b46-8f7e-9b9e7e111fc8',
      'Office Location',
      'Canada',
      'Toronto',
      'Ontario',
      '456 Business Ave');

INSERT INTO customer (id, firstname, lastname, username, password, email_address)
VALUES (
    '8e936d7d-76d8-47fe-80be-0993e348f877',
    'John',
    'Doe',
    'johndoe',
    'mysecretpassword',
    'john.doe@email.com'
);

INSERT INTO product_category (id, name, description)
VALUES (
    '3b3c678c-75ca-4b68-b94a-8be50e903911',
    'Electronics',
    'Electronic'
);

-- Product 1
INSERT INTO product (id, name, description, price, weight, category_id, supplier, image_url)
VALUES (
    'a0b3fba7-9c98-4673-82dd-14f9f5b7db28', 'Smartphone', 'Advanced features.', 799.99, 0.35, '3b3c678c-75ca-4b68-b94a-8be50e903911', 'Tech Store Inc.', 'https://example'
);

-- Product 2
INSERT INTO product (id, name, description, price, weight, category_id, supplier, image_url)
VALUES (
    'eb7ccdbe-27e5-4d2e-8230-2e2cbcf36ef2', 'Laptop', 'Powerful laptops.', 1299.99, 2.5, '3b3c678c-75ca-4b68-b94a-8be50e903911', 'Tech Gurus Ltd.', 'https://examp'
);

-- Product 3
INSERT INTO product (id, name, description, price, weight, category_id, supplier, image_url)
VALUES (
    'db91de3d-2a7f-42e6-8bfc-d7b244132c55', 'Headphones', 'Noise-canceling.', 199.99, 0.25, '3b3c678c-75ca-4b68-b94a-8be50e903911', 'AudioTech Solutions', 'https://example'
);

-- Stock for Product 1 in Location 1
INSERT INTO stock (product_id, location_id, quantity)
VALUES (
    'a0b3fba7-9c98-4673-82dd-14f9f5b7db28', -- Product 1 ID
    'f47ac10b-58cc-4372-a567-0e02b2c3d479', -- Location 1 ID
    100
);

-- Stock for Product 1 in Location 2
INSERT INTO stock (product_id, location_id, quantity)
VALUES (
    'a0b3fba7-9c98-4673-82dd-14f9f5b7db28', -- Product 1 ID
    '6d5d413b-56e5-4b46-8f7e-9b9e7e111fc8', -- Location 2 ID
    50
);

-- Stock for Product 2 in Location 1
INSERT INTO stock (product_id, location_id, quantity)
VALUES (
    'eb7ccdbe-27e5-4d2e-8230-2e2cbcf36ef2', -- Product 2 ID
    'f47ac10b-58cc-4372-a567-0e02b2c3d479', -- Location 1 ID
    75
);

-- Stock for Product 3 in Location 2
INSERT INTO stock (product_id, location_id, quantity)
VALUES (
    'db91de3d-2a7f-42e6-8bfc-d7b244132c55', -- Product 3 ID
    '6d5d413b-56e5-4b46-8f7e-9b9e7e111fc8', -- Location 2 ID
    30
);