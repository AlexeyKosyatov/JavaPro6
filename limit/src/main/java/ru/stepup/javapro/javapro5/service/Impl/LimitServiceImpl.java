package ru.stepup.javapro.javapro5.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stepup.javapro.javapro5.dto.ChangeRemainDto;
import ru.stepup.javapro.javapro5.entity.LimitEntity;
import ru.stepup.javapro.javapro5.repository.LimitRepository;
import ru.stepup.javapro.javapro5.service.LimitService;
import ru.stepup.javapro.javapro5.service.ParamService;

import java.math.BigDecimal;

@Service
public class LimitServiceImpl implements LimitService {

    private final LimitRepository limitRepository;
    private final ParamService paramService;

    public LimitServiceImpl(LimitRepository limitRepository, ParamService paramService) {
        this.limitRepository = limitRepository;
        this.paramService = paramService;
    }

    @Override
    @Transactional
    public ChangeRemainDto changeRemain(Long userId, BigDecimal sum) {
        var entityOpt = limitRepository.findFirstByUserId(userId);

        LimitEntity entity;
        if(entityOpt.isPresent()) {
            entity = entityOpt.get();
        } else {
            entity = new LimitEntity();
            entity.setUserId(userId);
            entity.setRemainSum(paramService.getDefaultLimit());
        }

        if(entity.getRemainSum().compareTo(sum) < 0) {
            return new ChangeRemainDto("01");
        }
        else {
            entity.setRemainSum(entity.getRemainSum().add(sum.negate()));
            limitRepository.save(entity);
            return new ChangeRemainDto("00");
        }



    }

    @Override
    public void deleteAll() {
        limitRepository.deleteAll();
    }
}

