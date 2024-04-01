package ru.stepup.spring.coins.core.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.stepup.spring.coins.core.api.ExecuteCoinsRequest;
import ru.stepup.spring.coins.core.api.ExecuteCoinsResponse;
import ru.stepup.spring.coins.core.exceptions.BadRequestException;
import ru.stepup.spring.coins.core.configurations.properties.CoreProperties;
import ru.stepup.spring.coins.core.integrations.dtos.ChangeRemainDto;
import ru.stepup.spring.coins.core.services.impl.LimitServiceImpl;

import java.math.BigDecimal;

@Service
public class CoinsService {
    private final CoreProperties coreProperties;
    private final ExecutorService executorService;
    private final ProductService productService;
    private final LimitService limitService;
    private static final Logger logger = LoggerFactory.getLogger(CoinsService.class.getName());

    public CoinsService(CoreProperties coreProperties, ExecutorService executorService, ProductService productService, LimitServiceImpl limitService) {
        this.coreProperties = coreProperties;
        this.executorService = executorService;
        this.productService = productService;
        this.limitService = limitService;
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

        ChangeRemainDto changeRemainDto = limitService.changeRemain(product.getUserId(), request.sum());

        assert changeRemainDto != null;
        if(changeRemainDto.getCode() == ChangeRemainDto.ChangeRemainCode.CODE_NO) {
            throw new BadRequestException("Превышен дневной лимит", "OUT_LIMIT");
        }

        ExecuteCoinsResponse response = null;

        try {
            response = executorService.execute(request);
        }
        catch(Exception e) {
            logger.error("execute: {}", e.getMessage());
            try {
                limitService.changeRemain(product.getUserId(), request.sum().negate());
            }
            catch(Exception e1) {
                logger.error("Не удалось восстановить лимит после ошибки выполнения: {}, {}"
                        , e1.getMessage(), e.getMessage());
                throw new BadRequestException(e.getMessage() + ", " + e1.getMessage(), "BAD_INTEGRATION");
            }
            throw new BadRequestException(e.getMessage(), "BAD_INTEGRATION");
        }

        return response;
    }
}
