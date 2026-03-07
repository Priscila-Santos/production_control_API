package com.production.production_control.mapper;

import com.production.production_control.dto.response.ProductRawMaterialResponse;
import com.production.production_control.entity.ProductRawMaterial;
import org.springframework.stereotype.Component;

@Component
public class ProductRawMaterialMapper {

    public ProductRawMaterialResponse toDTO(ProductRawMaterial prm) {

        return new ProductRawMaterialResponse(
                prm.getProduct().getId(),
                prm.getRawMaterial().getId(),
                prm.getRawMaterial().getName(),
                prm.getRequiredQuantity()
        );
    }
}
