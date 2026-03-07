CREATE TABLE product_raw_materials (
                                       product_id BIGINT NOT NULL,
                                       raw_material_id BIGINT NOT NULL,
                                       required_quantity NUMERIC(15,3) NOT NULL CHECK (required_quantity > 0),

                                       PRIMARY KEY (product_id, raw_material_id),

                                       CONSTRAINT fk_prm_product
                                           FOREIGN KEY (product_id)
                                               REFERENCES products(id)
                                               ON DELETE CASCADE,

                                       CONSTRAINT fk_prm_raw_material
                                           FOREIGN KEY (raw_material_id)
                                               REFERENCES raw_materials(id)
                                               ON DELETE CASCADE
);