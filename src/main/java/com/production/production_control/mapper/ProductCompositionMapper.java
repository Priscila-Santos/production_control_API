package com.production.production_control.mapper;

import com.production.production_control.dto.response.ProductCompositionResponse;
import com.production.production_control.entity.ProductRawMaterial;
import org.springframework.stereotype.Component;

@Component
public class ProductCompositionMapper {

    public ProductCompositionResponse toDTO(ProductRawMaterial prm) {

        return new ProductCompositionResponse(
                prm.getProduct().getId(),
                prm.getRawMaterial().getId(),
                prm.getRawMaterial().getName(),
                prm.getRequiredQuantity()
        );
    }
}
