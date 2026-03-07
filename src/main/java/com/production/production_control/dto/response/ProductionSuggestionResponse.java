package com.production.production_control.dto.response;

import java.math.BigDecimal;

public record ProductionSuggestionResponse(

        Long productId,
        String productName,
        int quantity,
        BigDecimal unitPrice,
        BigDecimal totalValue

) {}
