package ru.stepup.spring.coins.core.services;

import org.springframework.stereotype.Service;
import ru.stepup.spring.coins.core.api.ExecuteCoinsRequest;
import ru.stepup.spring.coins.core.api.ExecuteCoinsResponse;
import ru.stepup.spring.coins.core.exceptions.BadRequestException;
import ru.stepup.spring.coins.core.configurations.properties.CoreProperties;

import java.math.BigDecimal;

@Service
public class CoinsService {
    private final CoreProperties coreProperties;
    private final ExecutorService executorService;
    private final ProductService productService;

    public CoinsService(CoreProperties coreProperties, ExecutorService executorService, ProductService productService) {
        this.coreProperties = coreProperties;
        this.executorService = executorService;
        this.productService = productService;
    }

    public ExecuteCoinsResponse execute(ExecuteCoinsRequest request) {
        if (coreProperties.getNumbersBlockingEnabled()) {
            if (coreProperties.getBlockedNumbers().contains(request.number())) {
                throw new BadRequestException("Указан заблокированный номер кошелька", "BLOCKED_ACCOUNT_NUMBER");
            }
        }

        var product = productService.product(Long.valueOf(request.productId()));

        if(product == null) {
            throw new BadRequestException("Не найден продукт", "PRODUCT_NOT_FOUND");
        }

        if(product.getBalance().compareTo(BigDecimal.valueOf(0)) < 0) {
            throw new BadRequestException("Отрицательный баланс", "LOW_BALANCE");
        }

        ExecuteCoinsResponse response = executorService.execute(request);
        return response;
    }
}
