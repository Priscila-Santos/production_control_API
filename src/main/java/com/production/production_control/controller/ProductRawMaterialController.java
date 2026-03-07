package com.production.production_control.controller;

import com.production.production_control.dto.request.ProductRawMaterialRequest;
import com.production.production_control.dto.response.ProductRawMaterialResponse;
import com.production.production_control.service.ProductRawMaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-materials")
@RequiredArgsConstructor
public class ProductRawMaterialController {

    private final ProductRawMaterialService service;

    @PostMapping
    public ProductRawMaterialResponse create(
            @Valid @RequestBody ProductRawMaterialRequest request) {

        return service.create(request);
    }

    @GetMapping("/product/{productId}")
    public List<ProductRawMaterialResponse> listByProduct(
            @PathVariable Long productId) {

        return service.findByProduct(productId);
    }

    @DeleteMapping
    public void delete(
            @RequestParam Long productId,
            @RequestParam Long rawMaterialId) {

        service.delete(productId, rawMaterialId);
    }
}