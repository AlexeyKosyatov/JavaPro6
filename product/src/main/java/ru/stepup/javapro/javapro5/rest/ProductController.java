package ru.stepup.javapro.javapro5.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.stepup.javapro.javapro5.dto.ProductDto;
import ru.stepup.javapro.javapro5.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/filter")
    public List<ProductDto> getAllProductsByUserId(
            @RequestParam Long userId
    ) {
        return productService.selectAllDtoByUserId(userId);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(
            @PathVariable Long id
    ) {
        return productService.selectDtoById(id);
    }

}
