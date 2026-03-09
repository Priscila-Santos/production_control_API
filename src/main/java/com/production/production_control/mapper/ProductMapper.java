package com.production.production_control.mapper;

import com.production.production_control.dto.request.ProductRequest;
import com.production.production_control.dto.response.ProductCompositionResponse;
import com.production.production_control.dto.response.ProductResponse;
import com.production.production_control.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductMapper {

    public ProductResponse toDTO(Product product) {

        List<ProductCompositionResponse> materials =
                product.getRawMaterials() == null
                        ? List.of()
                        : product.getRawMaterials()
                        .stream()
                        .map(prm -> new ProductCompositionResponse(
                                product.getId(),
                                prm.getRawMaterial().getId(),
                                prm.getRawMaterial().getName(),
                                prm.getRequiredQuantity()
                        ))
                        .toList();

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                materials
        );
    }

    public Product toEntity(ProductRequest request) {

        return Product.builder()
                .name(request.name())
                .price(request.price())
                .build();
    }
}