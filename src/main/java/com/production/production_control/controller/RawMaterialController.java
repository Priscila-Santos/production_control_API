package com.production.production_control.controller;

import com.production.production_control.dto.request.RawMaterialRequest;
import com.production.production_control.dto.response.RawMaterialResponse;
import com.production.production_control.service.RawMaterialService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/raw-materials")
@RequiredArgsConstructor
public class RawMaterialController {

    private final RawMaterialService service;

    @PostMapping
    public RawMaterialResponse create(
            @Valid @RequestBody RawMaterialRequest request) {

        return service.create(request);
    }

    @GetMapping
    public List<RawMaterialResponse> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public RawMaterialResponse findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public RawMaterialResponse update(
            @PathVariable Long id,
            @Valid @RequestBody RawMaterialRequest request) {

        return service.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
