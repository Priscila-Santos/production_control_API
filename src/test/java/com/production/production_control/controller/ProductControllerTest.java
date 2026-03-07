package com.production.production_control.controller;

import com.production.production_control.dto.request.ProductRequest;
import com.production.production_control.dto.response.ProductResponse;
import com.production.production_control.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @Test
    void shouldCreateProduct() {

        // Arrange
        ProductRequest request = new ProductRequest("Chair", BigDecimal.valueOf(500));

        ProductResponse response =
                new ProductResponse(1L, "Chair", BigDecimal.valueOf(500), List.of());

        when(productService.create(request)).thenReturn(response);

        // Act
        ResponseEntity<ProductResponse> result = productController.create(request);

        // Assert
        assertEquals(200, result.getStatusCode().value());
        assertEquals(response, result.getBody());

        verify(productService).create(request);
    }

    @Test
    void shouldReturnAllProducts() {

        // Arrange
        ProductResponse product =
                new ProductResponse(1L, "Chair", BigDecimal.valueOf(500), List.of());

        when(productService.findAll()).thenReturn(List.of(product));

        // Act
        List<ProductResponse> result = productController.findAll();

        // Assert
        assertEquals(1, result.size());
        assertEquals("Chair", result.get(0).name());

        verify(productService).findAll();
    }

    @Test
    void shouldReturnProductById() {

        // Arrange
        Long id = 1L;

        ProductResponse response =
                new ProductResponse(id, "Chair", BigDecimal.valueOf(500), List.of());

        when(productService.findById(id)).thenReturn(response);

        // Act
        ProductResponse result = productController.findById(id);

        // Assert
        assertEquals(id, result.id());
        assertEquals("Chair", result.name());

        verify(productService).findById(id);
    }

    @Test
    void shouldUpdateProduct() {

        // Arrange
        Long id = 1L;

        ProductRequest request =
                new ProductRequest("Updated Chair", BigDecimal.valueOf(600));

        ProductResponse response =
                new ProductResponse(id, "Updated Chair", BigDecimal.valueOf(600), List.of());

        when(productService.update(id, request)).thenReturn(response);

        // Act
        ProductResponse result = productController.update(id, request);

        // Assert
        assertEquals("Updated Chair", result.name());
        assertEquals(BigDecimal.valueOf(600), result.price());

        verify(productService).update(id, request);
    }

    @Test
    void shouldDeleteProduct() {

        // Arrange
        Long id = 1L;

        doNothing().when(productService).delete(id);

        // Act
        ResponseEntity<Void> result = productController.delete(id);

        // Assert
        assertEquals(204, result.getStatusCode().value());

        verify(productService).delete(id);
    }

    @Test
    void shouldProduceProduct() {

        // Arrange
        Long id = 1L;
        int quantity = 5;

        doNothing().when(productService).produceProduct(id, quantity);

        // Act
        ResponseEntity<Void> result = productController.produce(id, quantity);

        // Assert
        assertEquals(200, result.getStatusCode().value());

        verify(productService).produceProduct(id, quantity);
    }
}