package com.production.production_control.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record DashboardResponse(

        Long totalProducts,
        DashboardTrendResponse productsTrend,

        Long totalRawMaterials,
        DashboardTrendResponse rawMaterialsTrend,

        BigDecimal totalStockQuantity,
        DashboardTrendResponse stockTrend,

        BigDecimal estimatedProductionValue,
        DashboardTrendResponse productionTrend,

        List<DashboardStockDistributionResponse> stockDistribution,
        List<DashboardProductionTrendResponse> productionValueTrend

) {}