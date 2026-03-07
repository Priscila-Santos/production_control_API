package com.production.production_control.service;

import com.production.production_control.dto.request.ProductRawMaterialRequest;
import com.production.production_control.dto.response.ProductRawMaterialResponse;
import com.production.production_control.entity.*;
import com.production.production_control.mapper.ProductRawMaterialMapper;
import com.production.production_control.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductRawMaterialService {

    private final ProductRepository productRepository;
    private final RawMaterialRepository rawMaterialRepository;
    private final ProductRawMaterialRepository repository;
    private final ProductRawMaterialMapper mapper;

    public ProductRawMaterialResponse create(ProductRawMaterialRequest request) {

        Product product = productRepository.findById(request.productId())
                .orElseThrow();

        RawMaterial rawMaterial = rawMaterialRepository.findById(request.rawMaterialId())
                .orElseThrow();

        ProductRawMaterialId id = new ProductRawMaterialId(
                product.getId(),
                rawMaterial.getId()
        );

        ProductRawMaterial prm = ProductRawMaterial.builder()
                .id(id)
                .product(product)
                .rawMaterial(rawMaterial)
                .requiredQuantity(request.requiredQuantity())
                .build();

        repository.save(prm);

        return mapper.toDTO(prm);
    }

    public List<ProductRawMaterialResponse> findByProduct(Long productId) {

        return repository.findByProductId(productId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public void delete(Long productId, Long rawMaterialId) {

        ProductRawMaterialId id =
                new ProductRawMaterialId(productId, rawMaterialId);

        repository.deleteById(id);
    }
}