package com.production.production_control.mapper;

import com.production.production_control.dto.request.RawMaterialRequest;
import com.production.production_control.dto.response.RawMaterialResponse;
import com.production.production_control.entity.RawMaterial;
import org.springframework.stereotype.Component;

@Component
public class RawMaterialMapper {

    public RawMaterial toEntity(RawMaterialRequest request) {

        return RawMaterial.builder()
                .name(request.name())
                .stockQuantity(request.stockQuantity())
                .build();
    }

    public RawMaterialResponse toDTO(RawMaterial rawMaterial) {

        return new RawMaterialResponse(
                rawMaterial.getId(),
                rawMaterial.getName(),
                rawMaterial.getStockQuantity()
        );
    }
}
