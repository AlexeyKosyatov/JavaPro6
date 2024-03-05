package ru.stepup.spring.coins.core.services;

import org.springframework.stereotype.Service;
import ru.stepup.spring.coins.core.integrations.ProductIntegration;
import ru.stepup.spring.coins.core.integrations.dtos.ProductDto;

import java.util.List;

@Service
public class ProductService {
    private final ProductIntegration productIntegration;

    public ProductService(ProductIntegration productIntegration) {
        this.productIntegration = productIntegration;
    }

    public List<ProductDto> products(Long userId) {
        return productIntegration.products(userId);
    }

    public ProductDto product(Long id) {
        return productIntegration.product(id);
    }
}
