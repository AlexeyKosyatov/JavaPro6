package ru.stepup.spring.coins.core.integrations;

import ru.stepup.spring.coins.core.integrations.dtos.ProductDto;

import java.util.List;

public interface ProductIntegration {
    List<ProductDto> products(Long userId);
    ProductDto product(Long id);
}