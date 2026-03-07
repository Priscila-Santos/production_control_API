package com.production.production_control.dto.response;

import java.math.BigDecimal;

public record RawMaterialResponse(

        Long id,
        String name,
        BigDecimal stockQuantity

) {}