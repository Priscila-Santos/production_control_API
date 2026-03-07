package com.production.production_control.controller;

import com.production.production_control.dto.request.ProductRequest;
import com.production.production_control.dto.response.ProductResponse;
import com.production.production_control.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> create(
            @Valid @RequestBody ProductRequest request) {

        return ResponseEntity.ok(productService.create(request));
    }

    @GetMapping
    public List<ProductResponse> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductResponse findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PutMapping("/{id}")
    public ProductResponse update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {

        return productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        productService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/produce")
    public ResponseEntity<Void> produce(
            @PathVariable Long id,
            @RequestParam int quantity) {

        productService.produceProduct(id, quantity);

        return ResponseEntity.ok().build();
    }
}