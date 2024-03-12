package ru.stepup.javapro.javapro5.service.Impl;

import org.springframework.stereotype.Service;
import ru.stepup.javapro.javapro5.dto.ProductDto;
import ru.stepup.javapro.javapro5.entity.ProductEntity;
import ru.stepup.javapro.javapro5.repository.ProductRepository;
import ru.stepup.javapro.javapro5.service.ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void create(ProductDto productDto) {
        var productEntity = new ProductEntity(
                productDto.getId().longValue(),
                productDto.getAccountNumber(),
                productDto.getBalance(),
                productDto.getType(),
                productDto.getUserId());
        productRepository.save(productEntity);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

    @Override
    public List<ProductDto> selectAllDtoByUserId(Long userId) {
        return productRepository.findAllByUserId(userId)
                .stream()
                .map(productEntity -> new ProductDto(
                        productEntity.getId(),
                        productEntity.getAccountNumber(),
                        productEntity.getBalance(),
                        productEntity.getType(),
                        productEntity.getUserId()))
                .toList();
    }

    @Override
    public ProductDto selectDtoById(Long id) {
        var productEntity = productRepository.findById(id);
        if(productEntity.isPresent()) {
            return new ProductDto(
                    productEntity.get().getId(),
                    productEntity.get().getAccountNumber(),
                    productEntity.get().getBalance(),
                    productEntity.get().getType(),
                    productEntity.get().getUserId());
        }
        else {
            throw new RuntimeException("Продукт не найден id = " + id);
        }
    }

    @Override
    public List<ProductEntity> selectAllEntityByUserId(Long userId) {
        return productRepository.findAllByUserId(userId);
    }

    @Override
    public Optional<ProductEntity> selectEntityById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}

