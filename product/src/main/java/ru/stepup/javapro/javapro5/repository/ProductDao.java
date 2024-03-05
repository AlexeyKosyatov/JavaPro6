package ru.stepup.javapro.javapro5.repository;

import org.springframework.stereotype.Component;
import ru.stepup.javapro.javapro5.entity.ProductEntity;

import java.util.List;

@Component
public interface ProductDao {

    void create(ProductEntity productEntity);

    void deleteAll();

    List<ProductEntity> selectAllByUserId(Long userId);

    ProductEntity selectById(Long id);

    void deleteById(Long id);
}