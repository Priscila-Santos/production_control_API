package com.production.production_control.repository;

import com.production.production_control.entity.ProductRawMaterial;
import com.production.production_control.entity.ProductRawMaterialId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRawMaterialRepository
        extends JpaRepository<ProductRawMaterial, ProductRawMaterialId> {

    List<ProductRawMaterial> findByProductId(Long productId);
}
