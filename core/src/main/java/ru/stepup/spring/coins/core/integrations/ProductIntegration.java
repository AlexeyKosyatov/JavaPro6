package ru.stepup.spring.coins.core.integrations;

import org.springframework.http.ResponseEntity;
import ru.stepup.spring.coins.core.integrations.dtos.ProductDto;

import java.util.List;

public interface ProductIntegration {
    ResponseEntity<List<ProductDto>> products(Long userId);
    ProductDto product(Long id);
}