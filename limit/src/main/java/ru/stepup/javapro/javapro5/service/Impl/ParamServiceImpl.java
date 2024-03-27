package ru.stepup.javapro.javapro5.service.Impl;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stepup.javapro.javapro5.entity.ParamEntity;
import ru.stepup.javapro.javapro5.repository.ParamRepository;
import ru.stepup.javapro.javapro5.service.ParamService;

import java.math.BigDecimal;

@Service
public class ParamServiceImpl implements ParamService {

    private final ParamRepository paramRepository;
    @Value("${limit.params.default-limit}")
    private BigDecimal defaultLimit;
    private static final String limitCode = "limit";

    public ParamServiceImpl(ParamRepository paramRepository) {
        this.paramRepository = paramRepository;
    }

    @Override
    @Transactional
    public void setDefaultLimit(BigDecimal limit) throws BadRequestException {
        if (BigDecimal.valueOf(0).compareTo(limit) >= 0 ) {
            throw new BadRequestException("Нельзя установить отрицательный лимит");
        }

        var entityOpt = paramRepository.findFirstByCode(limitCode);

        ParamEntity entity;
        if(entityOpt.isPresent()) {
            entity = entityOpt.get();
        } else {
            entity = new ParamEntity();
            entity.setCode(limitCode);
        }

        entity.setValue(limit);

        paramRepository.save(entity);
    }

    @Override
    public BigDecimal getDefaultLimit() {
        var entityOpt = paramRepository.findFirstByCode(limitCode);
        if(entityOpt.isPresent()) {
            return entityOpt.get().getValue();
        }

        return defaultLimit;
    }
}

