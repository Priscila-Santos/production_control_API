package com.production.production_control.repository;

import com.production.production_control.entity.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Optional;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {

    Optional<RawMaterial> findByName(String name);

    boolean existsByName(String name);

    @Query("SELECT COALESCE(SUM(r.stockQuantity), 0) FROM RawMaterial r")
    BigDecimal getTotalStockQuantity();

}
