package com.production.production_control.dto.response;

import java.math.BigDecimal;
import java.util.List;

public record ProductResponse (
        Long id,
        String name,
        BigDecimal price,
        List<ProductCompositionResponse> materials
) {
}
