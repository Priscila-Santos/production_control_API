package com.production.production_control.service;

import com.production.production_control.dto.response.ProductionSuggestionResponse;
import com.production.production_control.entity.Product;
import com.production.production_control.entity.ProductRawMaterial;
import com.production.production_control.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductionService {

    private final ProductRepository productRepository;

    public List<ProductionSuggestionResponse> calculateProduction() {

        List<Product> products = productRepository.findAllWithMaterials();

        // ordenar por maior valor
        products.sort(Comparator.comparing(Product::getPrice).reversed());

        Map<Long, BigDecimal> availableStock = new HashMap<>();

        // mapear estoque atual
        for (Product product : products) {
            for (ProductRawMaterial prm : product.getRawMaterials()) {

                Long id = prm.getRawMaterial().getId();

                availableStock.putIfAbsent(
                        id,
                        prm.getRawMaterial().getStockQuantity()
                );
            }
        }

        List<ProductionSuggestionResponse> result = new ArrayList<>();

        for (Product product : products) {

            int maxQuantity = Integer.MAX_VALUE;

            for (ProductRawMaterial prm : product.getRawMaterials()) {

                Long materialId = prm.getRawMaterial().getId();

                BigDecimal available = availableStock.get(materialId);

                BigDecimal required = prm.getRequiredQuantity();

                int possible = available
                        .divide(required, 0, BigDecimal.ROUND_DOWN)
                        .intValue();

                maxQuantity = Math.min(maxQuantity, possible);
            }

            if (maxQuantity <= 0) {
                continue;
            }

            // atualizar estoque virtual
            for (ProductRawMaterial prm : product.getRawMaterials()) {

                Long materialId = prm.getRawMaterial().getId();

                BigDecimal used =
                        prm.getRequiredQuantity()
                                .multiply(BigDecimal.valueOf(maxQuantity));

                availableStock.put(
                        materialId,
                        availableStock.get(materialId).subtract(used)
                );
            }

            BigDecimal total =
                    product.getPrice().multiply(BigDecimal.valueOf(maxQuantity));

            result.add(
                    new ProductionSuggestionResponse(
                            product.getId(),
                            product.getName(),
                            maxQuantity,
                            product.getPrice(),
                            total
                    )
            );
        }

        return result;
    }
}