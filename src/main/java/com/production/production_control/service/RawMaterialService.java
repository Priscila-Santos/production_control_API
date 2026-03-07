package com.production.production_control.service;

import com.production.production_control.dto.request.RawMaterialRequest;
import com.production.production_control.dto.response.RawMaterialResponse;
import com.production.production_control.entity.RawMaterial;
import com.production.production_control.mapper.RawMaterialMapper;
import com.production.production_control.repository.RawMaterialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RawMaterialService {

    private final RawMaterialRepository repository;
    private final RawMaterialMapper mapper;

    public RawMaterialResponse create(RawMaterialRequest request) {

        RawMaterial rawMaterial = mapper.toEntity(request);

        rawMaterial = repository.save(rawMaterial);

        return mapper.toDTO(rawMaterial);
    }

    public List<RawMaterialResponse> findAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public RawMaterialResponse findById(Long id) {

        RawMaterial rawMaterial = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Raw material not found"));

        return mapper.toDTO(rawMaterial);
    }

    public RawMaterialResponse update(Long id, RawMaterialRequest request) {

        RawMaterial rawMaterial = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Raw material not found"));

        rawMaterial.setName(request.name());
        rawMaterial.setStockQuantity(request.stockQuantity());

        rawMaterial = repository.save(rawMaterial);

        return mapper.toDTO(rawMaterial);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}