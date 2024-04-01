package ru.stepup.spring.coins.core.integrations;

import ru.stepup.spring.coins.core.integrations.dtos.ChangeRemainDto;

import java.math.BigDecimal;

public interface LimitIntegration {
    ChangeRemainDto changeRemain(Long userId, BigDecimal sum);
}