CREATE TABLE tb_products (
product_id BIGSERIAL PRIMARY KEY,
name VARCHAR(255) NOT NULL,
type VARCHAR(30) NOT NULL,
animal_type VARCHAR(30),
brand VARCHAR(30),
description TEXT NOT NULL,
stock INTEGER NOT NULL,
price DECIMAL(10,2) NOT NULL,
size_weight DECIMAL(5,2),
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
last_update TIMESTAMP,
deleted_product BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE tb_rating (
rating_id BIGSERIAL PRIMARY KEY,
product_id BIGINT NOT NULL,
stars VARCHAR (8) NOT NULL,
client VARCHAR(50) NOT NULL,
comments VARCHAR(255),
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
last_update TIMESTAMP,
deleted_rating BOOLEAN NOT NULL DEFAULT FALSE,
CONSTRAINT fk_product_rating FOREIGN KEY (product_id) REFERENCES tb_products (product_id)
);

CREATE TABLE tb_orders (
order_id BIGSERIAL PRIMARY KEY,
total_items_count INTEGER NOT NULL,
client VARCHAR(50) NOT NULL,
total_amount DECIMAL(10,2),
status  VARCHAR(20) NOT NULL,
order_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
order_update TIMESTAMP,
deleted_order BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE tb_products_orders (
    product_order_id BIGSERIAL PRIMARY KEY,
    product_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_update TIMESTAMP,
    deleted_product_order BOOLEAN DEFAULT FALSE,

    CONSTRAINT fk_product FOREIGN KEY (product_id) REFERENCES tb_products(product_id),
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES tb_orders(order_id)
);