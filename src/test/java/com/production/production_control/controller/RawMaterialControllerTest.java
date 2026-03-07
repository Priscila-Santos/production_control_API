package com.production.production_control.controller;

import com.production.production_control.dto.request.RawMaterialRequest;
import com.production.production_control.dto.response.RawMaterialResponse;
import com.production.production_control.service.RawMaterialService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RawMaterialControllerTest {

    @Mock
    private RawMaterialService service;

    @InjectMocks
    private RawMaterialController controller;

    @Test
    void shouldCreateRawMaterial() {

        // Arrange
        RawMaterialRequest request =
                new RawMaterialRequest("Steel", BigDecimal.valueOf(100));

        RawMaterialResponse response =
                new RawMaterialResponse(1L, "Steel", BigDecimal.valueOf(100));

        when(service.create(request)).thenReturn(response);

        // Act
        RawMaterialResponse result = controller.create(request);

        // Assert
        assertEquals("Steel", result.name());
        assertEquals(BigDecimal.valueOf(100), result.stockQuantity());

        verify(service).create(request);
    }

    @Test
    void shouldReturnAllRawMaterials() {

        // Arrange
        RawMaterialResponse response =
                new RawMaterialResponse(1L, "Steel", BigDecimal.valueOf(100));

        when(service.findAll()).thenReturn(List.of(response));

        // Act
        List<RawMaterialResponse> result = controller.findAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Steel", result.get(0).name());

        verify(service).findAll();
    }

    @Test
    void shouldReturnRawMaterialById() {

        // Arrange
        Long id = 1L;

        RawMaterialResponse response =
                new RawMaterialResponse(id, "Steel", BigDecimal.valueOf(100));

        when(service.findById(id)).thenReturn(response);

        // Act
        RawMaterialResponse result = controller.findById(id);

        // Assert
        assertEquals(id, result.id());
        assertEquals("Steel", result.name());

        verify(service).findById(id);
    }

    @Test
    void shouldUpdateRawMaterial() {

        // Arrange
        Long id = 1L;

        RawMaterialRequest request =
                new RawMaterialRequest("Updated Steel", BigDecimal.valueOf(200));

        RawMaterialResponse response =
                new RawMaterialResponse(id, "Updated Steel", BigDecimal.valueOf(200));

        when(service.update(id, request)).thenReturn(response);

        // Act
        RawMaterialResponse result = controller.update(id, request);

        // Assert
        assertEquals("Updated Steel", result.name());
        assertEquals(BigDecimal.valueOf(200), result.stockQuantity());

        verify(service).update(id, request);
    }

    @Test
    void shouldDeleteRawMaterial() {

        // Arrange
        Long id = 1L;

        doNothing().when(service).delete(id);

        // Act
        controller.delete(id);

        // Assert
        verify(service).delete(id);
    }
}