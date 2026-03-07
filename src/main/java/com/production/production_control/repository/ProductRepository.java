package com.production.production_control.repository;

import com.production.production_control.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByPriceDesc();

    @Query("""
            SELECT DISTINCT p
            FROM Product p
            LEFT JOIN FETCH p.rawMaterials prm
            LEFT JOIN FETCH prm.rawMaterial
            """)
    List<Product> findAllWithMaterials();
}
