package com.production.production_control.controller;

import com.production.production_control.dto.request.ProductRawMaterialRequest;
import com.production.production_control.dto.response.ProductRawMaterialResponse;
import com.production.production_control.service.ProductRawMaterialService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductRawMaterialControllerTest {

    @Mock
    private ProductRawMaterialService service;

    @InjectMocks
    private ProductRawMaterialController controller;

    @Test
    void shouldCreateProductRawMaterialRelation() {

        // Arrange
        ProductRawMaterialRequest request =
                new ProductRawMaterialRequest(
                        1L,
                        2L,
                        new BigDecimal("0.5")
                );

        ProductRawMaterialResponse response =
                new ProductRawMaterialResponse(
                        1L,
                        1L,
                        "Sugar",
                        new BigDecimal("0.5")
                );

        when(service.create(request)).thenReturn(response);

        // Act
        ProductRawMaterialResponse result = controller.create(request);

        // Assert
        assertNotNull(result);
        assertEquals("Sugar", result.rawMaterialName());

        verify(service, times(1)).create(request);
    }
}