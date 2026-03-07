package com.production.production_control.dto.response;

import java.math.BigDecimal;

public record ProductRawMaterialResponse(

        Long productId,
        Long rawMaterialId,
        String rawMaterialName,
        BigDecimal requiredQuantity

) {}