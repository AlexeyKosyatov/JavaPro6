package ru.stepup.spring.coins.core.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.stepup.spring.coins.core.integrations.LimitIntegration;
import ru.stepup.spring.coins.core.integrations.dtos.ChangeRemainDto;

import java.math.BigDecimal;

@Service
public class LimitService {
    private final LimitIntegration limitIntegration;

    public LimitService(LimitIntegration limitIntegration) {
        this.limitIntegration = limitIntegration;
    }

    public ResponseEntity<ChangeRemainDto> changeRemain(Long userId, BigDecimal sum) {
        return limitIntegration.changeRemain(userId, sum);
    }
}
