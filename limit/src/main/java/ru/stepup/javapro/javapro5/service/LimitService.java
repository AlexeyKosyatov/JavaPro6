package ru.stepup.javapro.javapro5.service;

import org.springframework.stereotype.Component;
import ru.stepup.javapro.javapro5.dto.ChangeRemainDto;

import java.math.BigDecimal;

@Component
public interface LimitService {
    ChangeRemainDto changeRemain(Long userId, BigDecimal sum);
    void deleteAll();
}

