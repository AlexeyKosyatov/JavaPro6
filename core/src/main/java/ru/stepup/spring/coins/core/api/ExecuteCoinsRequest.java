package ru.stepup.spring.coins.core.api;

import java.math.BigDecimal;

public record ExecuteCoinsRequest(
        String number,
        String productId,
        String productType,
        BigDecimal sum
) {
}
