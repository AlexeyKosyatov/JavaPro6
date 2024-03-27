package ru.stepup.javapro.javapro5.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.stepup.javapro.javapro5.service.LimitService;

@Component
public class ClearLimitsTask {

    private final LimitService limitService;

    public ClearLimitsTask(LimitService limitService) {
        this.limitService = limitService;
    }

    @Scheduled(cron = "${limit.cron}")
    public void execute() {
        try {
            limitService.deleteAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
