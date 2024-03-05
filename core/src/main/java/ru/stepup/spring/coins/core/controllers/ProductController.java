package ru.stepup.spring.coins.core.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.stepup.spring.coins.core.integrations.dtos.ProductDto;
import ru.stepup.spring.coins.core.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public List<ProductDto> execute(@RequestHeader Long userId) {
        return productService.products(userId);
    }
}

