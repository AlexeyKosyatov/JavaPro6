package ru.stepup.spring.coins.core.integrations;

import org.springframework.http.ResponseEntity;
import ru.stepup.spring.coins.core.integrations.dtos.ChangeRemainDto;

import java.math.BigDecimal;

public interface LimitIntegration {
    ResponseEntity<ChangeRemainDto> changeRemain(Long userId, BigDecimal sum);
}