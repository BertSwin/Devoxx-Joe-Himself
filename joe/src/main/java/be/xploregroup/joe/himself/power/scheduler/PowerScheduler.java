package be.xploregroup.joe.himself.power.scheduler;

import be.xploregroup.joe.himself.power.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by bertswinnen on 18/10/15.
 */
@Component
@EnableScheduling
public class PowerScheduler {
    @Autowired
    private PowerService powerService;

    @Scheduled(fixedDelayString = "${power.interval}")
    public void requirePower() {
        powerService.requirePower();
    }
}
