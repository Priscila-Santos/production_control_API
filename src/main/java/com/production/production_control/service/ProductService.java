package com.production.production_control.service;

import com.production.production_control.dto.request.ProductRequest;
import com.production.production_control.dto.response.ProductResponse;
import com.production.production_control.entity.Product;
import com.production.production_control.entity.ProductRawMaterial;
import com.production.production_control.entity.RawMaterial;
import com.production.production_control.mapper.ProductMapper;
import com.production.production_control.repository.ProductRepository;
import com.production.production_control.repository.RawMaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final RawMaterialRepository rawMaterialRepository;
    private final ProductMapper productMapper;

    public ProductResponse create(ProductRequest request) {

        Product product = productMapper.toEntity(request);

        product = productRepository.save(product);

        return productMapper.toDTO(product);
    }

    public List<ProductResponse> findAll() {

        return productRepository.findAll()
                .stream()
                .map(productMapper::toDTO)
                .toList();
    }

    public ProductResponse findById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return productMapper.toDTO(product);
    }

    public ProductResponse update(Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(request.name());
        product.setPrice(request.price());

        product = productRepository.save(product);

        return productMapper.toDTO(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void produceProduct(Long productId, int quantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow();

        for (ProductRawMaterial prm : product.getRawMaterials()) {

            RawMaterial material = prm.getRawMaterial();

            BigDecimal totalRequired =
                    prm.getRequiredQuantity().multiply(BigDecimal.valueOf(quantity));

            if (material.getStockQuantity().compareTo(totalRequired) < 0) {
                throw new RuntimeException(
                        "Not enough stock for material: " + material.getName()
                );
            }

            material.setStockQuantity(
                    material.getStockQuantity().subtract(totalRequired)
            );

            rawMaterialRepository.save(material);

        }


    }

}