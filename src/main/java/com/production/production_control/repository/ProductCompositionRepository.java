package com.production.production_control.repository;

import com.production.production_control.entity.ProductRawMaterial;
import com.production.production_control.entity.ProductComposition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductCompositionRepository
        extends JpaRepository<ProductRawMaterial, ProductComposition> {

    List<ProductRawMaterial> findByProductId(Long productId);

    void deleteByProductId(Long productId);
}
