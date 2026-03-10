package com.production.production_control.controller;

import com.production.production_control.dto.response.ProductionSuggestionResponse;
import com.production.production_control.service.ProductionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/production")
public class ProductionController {

    private final ProductionService productionService;

    public ProductionController(ProductionService productionService) {
        this.productionService = productionService;
    }

    @GetMapping("/suggestions")
    public List<ProductionSuggestionResponse> getProductionSuggestions() {
        return productionService.getProductionSuggestions();
    }
}


