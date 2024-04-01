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
    private static final String LIMIT_CODE = "limit";

    public ParamServiceImpl(ParamRepository paramRepository) {
        this.paramRepository = paramRepository;
    }

    @Override
    @Transactional
    public void setDefaultLimit(BigDecimal limit) throws BadRequestException {
        if (BigDecimal.valueOf(0).compareTo(limit) >= 0) {
            throw new BadRequestException("Нельзя установить отрицательный лимит");
        }

        var entity = paramRepository.findFirstByCode(LIMIT_CODE)
                .orElse(new ParamEntity());
        entity.setCode(LIMIT_CODE);
        entity.setValue(limit);

        paramRepository.save(entity);
    }

    @Override
    public BigDecimal getDefaultLimit() {
        return paramRepository.findFirstByCode(LIMIT_CODE)
                .map(ParamEntity::getValue)
                .orElse(defaultLimit);
    }
}

