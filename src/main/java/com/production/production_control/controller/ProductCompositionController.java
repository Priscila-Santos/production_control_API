package com.production.production_control.controller;

import com.production.production_control.dto.request.ProductCompositionRequest;
import com.production.production_control.dto.response.ProductCompositionResponse;
import com.production.production_control.service.ProductRawMaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-materials")
@RequiredArgsConstructor
public class ProductCompositionController {

    private final ProductRawMaterialService service;

    @PostMapping
    public ProductCompositionResponse create(
            @Valid @RequestBody ProductCompositionRequest request) {

        return service.create(request);
    }

    @GetMapping("/product/{productId}")
    public List<ProductCompositionResponse> listByProduct(
            @PathVariable Long productId) {

        return service.findByProduct(productId);
    }

    @DeleteMapping("/{productId}/{rawMaterialId}")
    public void delete(
            @PathVariable Long productId,
            @PathVariable Long rawMaterialId) {

        service.delete(productId, rawMaterialId);
    }
}