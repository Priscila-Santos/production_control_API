CREATE TABLE production (
                            id BIGSERIAL PRIMARY KEY,
                            product_id BIGINT NOT NULL,
                            quantity INTEGER NOT NULL,
                            production_date DATE NOT NULL,
                            total_value NUMERIC(12,2) NOT NULL,

                            CONSTRAINT fk_production_product
                                FOREIGN KEY (product_id)
                                    REFERENCES products(id)
);