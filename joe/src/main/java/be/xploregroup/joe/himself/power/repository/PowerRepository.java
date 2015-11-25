package be.xploregroup.joe.himself.power.repository;

import be.xploregroup.joe.himself.power.model.PowerOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

/**
 * Created by bertswinnen on 18/10/15.
 */
@Repository
public class PowerRepository {
    private PowerOutput powerOutput;
    private static Logger logger = LoggerFactory.getLogger(PowerRepository.class);

    public PowerRepository() {
    }

    @PostConstruct
    public void init() {
        this.powerOutput = new PowerOutput();
    }

    public double getDesiredPower() {
        return powerOutput.getDesiredPower();
    }

    public double getCurrentPower() {
        return powerOutput.getCurrentPower();
    }

    public void setDesiredPower(double watts) {
        powerOutput.setDesiredPower(watts);
    }

    public void setCurrentPower(double currentPower) {
        powerOutput.setCurrentPower(currentPower);
    }
}
