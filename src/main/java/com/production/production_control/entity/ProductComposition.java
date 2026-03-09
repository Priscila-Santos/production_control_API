package com.production.production_control.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ProductComposition implements Serializable {

    private Long productId;
    private Long rawMaterialId;
}