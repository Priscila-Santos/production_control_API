package com.production.production_control.controller;

import com.production.production_control.dto.request.ProductCompositionRequest;
import com.production.production_control.dto.response.ProductCompositionResponse;
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
    private ProductCompositionController controller;

    @Test
    void shouldCreateProductRawMaterialRelation() {

        // Arrange
        ProductCompositionRequest request =
                new ProductCompositionRequest(
                        1L,
                        2L,
                        new BigDecimal("0.5")
                );

        ProductCompositionResponse response =
                new ProductCompositionResponse(
                        1L,
                        1L,
                        "Sugar",
                        new BigDecimal("0.5")
                );

        when(service.create(request)).thenReturn(response);

        // Act
        ProductCompositionResponse result = controller.create(request);

        // Assert
        assertNotNull(result);
        assertEquals("Sugar", result.rawMaterialName());

        verify(service, times(1)).create(request);
    }
}