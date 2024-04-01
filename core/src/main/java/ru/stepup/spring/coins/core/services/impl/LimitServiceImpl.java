package ru.stepup.spring.coins.core.services.impl;

import org.springframework.stereotype.Service;
import ru.stepup.spring.coins.core.integrations.LimitIntegration;
import ru.stepup.spring.coins.core.integrations.dtos.ChangeRemainDto;
import ru.stepup.spring.coins.core.services.LimitService;

import java.math.BigDecimal;

@Service
public class LimitServiceImpl implements LimitService {
    private final LimitIntegration limitIntegration;

    public LimitServiceImpl(LimitIntegration limitIntegration) {
        this.limitIntegration = limitIntegration;
    }

    @Override
    public ChangeRemainDto changeRemain(Long userId, BigDecimal sum) {
        return limitIntegration.changeRemain(userId, sum);
    }
}
