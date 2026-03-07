package com.production.production_control.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "raw_materials")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RawMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "stock_quantity", nullable = false, precision = 15, scale = 3)
    private BigDecimal stockQuantity;

    @OneToMany(mappedBy = "rawMaterial", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ProductRawMaterial> products = new HashSet<>();

}
