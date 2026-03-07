package com.production.production_control.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record RawMaterialRequest(

        @NotBlank
        String name,

        @NotNull
        @PositiveOrZero
        BigDecimal stockQuantity

) {}
