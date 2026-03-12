package com.production.production_control.dto.response;

import java.math.BigDecimal;

public record DashboardProductionTrendResponse(
        String month,
        BigDecimal value
) {}
