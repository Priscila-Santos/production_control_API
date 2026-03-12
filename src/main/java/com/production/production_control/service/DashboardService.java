package com.production.production_control.service;

import com.production.production_control.dto.response.*;
import com.production.production_control.repository.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final ProductRepository productRepository;
    private final RawMaterialRepository rawMaterialRepository;
    private final ProductionRepository productionRepository;

    public DashboardResponse getDashboard(){

        Long totalProducts = productRepository.count();
        Long totalRawMaterials = rawMaterialRepository.count();

        BigDecimal totalStockQuantity =
                rawMaterialRepository.getTotalStockQuantity();

        List<DashboardProductionTrendResponse> productionTrend =
                getProductionTrend();

        BigDecimal estimatedProductionValue =
                productionTrend.stream()
                        .map(DashboardProductionTrendResponse::value)
                        .reduce(BigDecimal.ZERO,BigDecimal::add);

        List<DashboardStockDistributionResponse> stockDistribution =
                calculateStockDistribution();

        return new DashboardResponse(

                totalProducts,
                new DashboardTrendResponse("12%",true),

                totalRawMaterials,
                new DashboardTrendResponse("5%",true),

                totalStockQuantity,
                new DashboardTrendResponse("8%",false),

                estimatedProductionValue,
                new DashboardTrendResponse("15%",true),

                stockDistribution,
                productionTrend
        );
    }

    private List<DashboardProductionTrendResponse> getProductionTrend(){

        return productionRepository.getProductionTrendRaw()
                .stream()
                .map(row -> new DashboardProductionTrendResponse(
                        (String) row[0],
                        (BigDecimal) row[1]
                ))
                .toList();
    }

    private List<DashboardStockDistributionResponse> calculateStockDistribution(){

        long inStock = rawMaterialRepository.findAll()
                .stream()
                .filter(r -> r.getStockQuantity().doubleValue() > 50)
                .count();

        long lowStock = rawMaterialRepository.findAll()
                .stream()
                .filter(r -> r.getStockQuantity().doubleValue() > 0
                        && r.getStockQuantity().doubleValue() <= 50)
                .count();

        long outOfStock = rawMaterialRepository.findAll()
                .stream()
                .filter(r -> r.getStockQuantity().doubleValue() == 0)
                .count();

        return List.of(

                new DashboardStockDistributionResponse("In Stock",inStock),
                new DashboardStockDistributionResponse("Low Stock",lowStock),
                new DashboardStockDistributionResponse("Out of Stock",outOfStock)

        );
    }
}