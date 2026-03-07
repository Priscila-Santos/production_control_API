package com.production.production_control.repository;

import com.production.production_control.entity.RawMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RawMaterialRepository extends JpaRepository<RawMaterial, Long> {

    Optional<RawMaterial> findByName(String name);

    boolean existsByName(String name);
}
