package com.production.production_control.controller;

import com.production.production_control.dto.response.ProductionSuggestionResponse;
import com.production.production_control.service.ProductionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/production")
@RequiredArgsConstructor
public class ProductionController {

    private final ProductionService productionService;

    @GetMapping("/suggestion")
    public List<ProductionSuggestionResponse> calculateProduction() {
        return productionService.calculateProduction();
    }
}
