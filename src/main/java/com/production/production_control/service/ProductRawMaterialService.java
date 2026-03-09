package com.production.production_control.service;

import com.production.production_control.dto.request.ProductCompositionRequest;
import com.production.production_control.dto.response.ProductCompositionResponse;
import com.production.production_control.entity.*;
import com.production.production_control.mapper.ProductCompositionMapper;
import com.production.production_control.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductRawMaterialService {

    private final ProductRepository productRepository;
    private final RawMaterialRepository rawMaterialRepository;
    private final ProductCompositionRepository repository;
    private final ProductCompositionMapper mapper;

    public ProductCompositionResponse create(ProductCompositionRequest request) {

        Product product = productRepository.findById(request.productId())
                .orElseThrow();

        RawMaterial rawMaterial = rawMaterialRepository.findById(request.rawMaterialId())
                .orElseThrow();

        ProductComposition id = new ProductComposition(
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

    public List<ProductCompositionResponse> findByProduct(Long productId) {

        return repository.findByProductId(productId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public void delete(Long productId, Long rawMaterialId) {

        ProductComposition id =
                new ProductComposition(productId, rawMaterialId);

        repository.deleteById(id);
    }
}