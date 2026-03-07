package com.production.production_control.controller;

import com.production.production_control.dto.response.ProductionSuggestionResponse;
import com.production.production_control.service.ProductionService;
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
class ProductionControllerTest {

    @Mock
    private ProductionService productionService;

    @InjectMocks
    private ProductionController productionController;

    @Test
    void shouldReturnProductionSuggestions() {

        // Arrange
        ProductionSuggestionResponse suggestion =
                new ProductionSuggestionResponse(
                        1L,
                        "Chocolate Cake",
                        25,
                        new BigDecimal("10.00"),
                        new BigDecimal("250.00")
                );

        List<ProductionSuggestionResponse> mockResponse = List.of(suggestion);

        when(productionService.calculateProduction())
                .thenReturn(mockResponse);

        // Act
        List<ProductionSuggestionResponse> result =
                productionController.calculateProduction();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Chocolate Cake", result.get(0).productName());
        assertEquals(25, result.get(0).quantity());

        verify(productionService, times(1))
                .calculateProduction();
    }
}