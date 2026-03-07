package com.production.production_control.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRawMaterialRequest(

        @NotNull
        Long productId,

        @NotNull
        Long rawMaterialId,

        @Positive
        BigDecimal requiredQuantity

) {}
