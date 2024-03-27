package ru.stepup.javapro.javapro5.service;

import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public interface ParamService {
    BigDecimal getDefaultLimit();
    void setDefaultLimit(BigDecimal limit) throws BadRequestException;
}

