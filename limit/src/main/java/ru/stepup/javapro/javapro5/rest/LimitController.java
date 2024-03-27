package ru.stepup.javapro.javapro5.rest;

import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.stepup.javapro.javapro5.dto.ChangeRemainDto;
import ru.stepup.javapro.javapro5.service.LimitService;
import ru.stepup.javapro.javapro5.service.ParamService;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1")
public class LimitController {

    private final LimitService limitService;
    private final ParamService paramService;

    public LimitController(LimitService limitService, ParamService paramService) {
        this.limitService = limitService;
        this.paramService = paramService;
    }

    @GetMapping("/change-remain")
    public ChangeRemainDto checkRemain(
            @RequestParam Long userId, @RequestParam BigDecimal sum
    ) {
        return limitService.changeRemain(userId, sum);
    }

    @PutMapping("/limit/default")
    public void setDefaultLimit(
            @RequestParam BigDecimal sum
    ) throws BadRequestException {
        paramService.setDefaultLimit(sum);
    }

}
