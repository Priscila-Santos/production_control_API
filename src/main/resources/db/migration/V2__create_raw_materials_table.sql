CREATE TABLE raw_materials (
                               id SERIAL PRIMARY KEY,
                               name VARCHAR(255) NOT NULL UNIQUE,
                               stock_quantity NUMERIC(15,3) NOT NULL CHECK (stock_quantity >= 0)
);