package ru.stepup.spring.coins.core.services;

import ru.stepup.spring.coins.core.integrations.dtos.ChangeRemainDto;

import java.math.BigDecimal;

public interface LimitService {
    ChangeRemainDto changeRemain(Long userId, BigDecimal sum);
}
