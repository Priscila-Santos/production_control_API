package com.production.production_control.service;

import com.production.production_control.dto.response.ProductionSuggestionResponse;
import com.production.production_control.entity.Product;
import com.production.production_control.entity.ProductRawMaterial;
import com.production.production_control.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductionService {

    private final ProductRepository productRepository;

    public ProductionService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductionSuggestionResponse> getProductionSuggestions() {

        List<Product> products = productRepository.findAllWithRawMaterials();

        List<ProductionSuggestionResponse> result = new ArrayList<>();

        for (Product product : products) {

            int possibleProduction = calculatePossibleProduction(product);

            BigDecimal totalValue =
                    product.getPrice().multiply(BigDecimal.valueOf(possibleProduction));

            result.add(
                    new ProductionSuggestionResponse(
                            product.getId(),
                            product.getName(),
                            possibleProduction,
                            product.getPrice(),
                            totalValue
                    )
            );
        }

        return result;
    }

    private int calculatePossibleProduction(Product product) {

        if (product.getRawMaterials() == null || product.getRawMaterials().isEmpty()) {
            return 0;
        }

        BigDecimal minProduction = null;

        for (ProductRawMaterial prm : product.getRawMaterials()) {

            BigDecimal stock = prm.getRawMaterial().getStockQuantity();
            BigDecimal required = prm.getRequiredQuantity();

            BigDecimal possible = stock.divide(required, 0, RoundingMode.DOWN);

            if (minProduction == null || possible.compareTo(minProduction) < 0) {
                minProduction = possible;
            }
        }

        return minProduction != null ? minProduction.intValue() : 0;
    }
}
