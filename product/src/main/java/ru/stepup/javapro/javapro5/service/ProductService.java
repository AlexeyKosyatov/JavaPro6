package ru.stepup.javapro.javapro5.service;

import org.springframework.stereotype.Component;
import ru.stepup.javapro.javapro5.dto.ProductDto;
import ru.stepup.javapro.javapro5.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

@Component
public interface ProductService {
    void create(ProductDto productDto);
    void deleteAll();
    List<ProductDto> selectAllDtoByUserId(Long userId);
    ProductDto selectDtoById(Long id);
    List<ProductEntity> selectAllEntityByUserId(Long userId);
    Optional<ProductEntity> selectEntityById(Long id);
    void deleteById(Long id);
}

