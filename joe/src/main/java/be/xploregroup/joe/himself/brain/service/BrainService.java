package be.xploregroup.joe.himself.brain.service;

import be.xploregroup.joe.himself.brain.dto.RequestedPaceDTO;
import be.xploregroup.joe.himself.power.dto.DesiredPowerDTO;
import be.xploregroup.joe.himself.power.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bertswinnen on 13/10/15.
 */
@Service
public class BrainService {
    private static double GRAVITY = 0;
    private static double ROLLING = 4.0697805;
    private static double DRIVETRAIN_LOSS = 3;

    @Autowired
    private PowerService powerService;

    public DesiredPowerDTO requestPace(RequestedPaceDTO requestedPaceDTO) {
        double requiredPower = calculatePower(requestedPaceDTO.getPace());

        DesiredPowerDTO desiredPowerDTO = new DesiredPowerDTO(requiredPower);

        powerService.setDesiredPower(desiredPowerDTO);

        return desiredPowerDTO;
    }

    protected double calculatePower(int velocity) {
        // calculate the forces on the rider.
        double totalforce = GRAVITY + ROLLING + calculateDrag(velocity);

        // calculate necessary wheelpower
        double wheelpower = totalforce * (velocity * 1000.0 / 3600.0);

        // calculate necessary legpower
        double legpower = wheelpower / (1.0 - (DRIVETRAIN_LOSS / 100.0));

        return legpower;
    }

    protected double calculateDrag(int velocity) {
        double drag = 0.5 *
                (0.509) *
                (0.63) *
                (1.226) *
                (velocity * 1000.0 / 3600.0) *
                (velocity * 1000.0 / 3600.0);

        return drag;
    }
}
