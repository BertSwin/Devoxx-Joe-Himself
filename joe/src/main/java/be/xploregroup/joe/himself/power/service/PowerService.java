package be.xploregroup.joe.himself.power.service;

import be.xploregroup.joe.himself.oxygen.repository.OxygenRepository;
import be.xploregroup.joe.himself.power.dto.DesiredPowerDTO;
import be.xploregroup.joe.himself.power.dto.PowerDTO;
import be.xploregroup.joe.himself.power.repository.PowerRepository;
import be.xploregroup.joe.oxygen.dto.OxygenObtainedDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Service concerned about Power
 * <p/>
 * Created by bertswinnen on 28/09/15.
 */
@Service
public class PowerService {
    public static final double POWER_TO_OXYGEN_RATIO = 10;

    @Autowired
    private PowerRepository powerRepository;

    @Autowired
    private OxygenRepository oxygenRepository;

    public void setDesiredPower(DesiredPowerDTO desiredPowerDTO) {
        powerRepository.setDesiredPower(desiredPowerDTO.getWatts());
    }

    public DesiredPowerDTO getDesiredPower() {
        DesiredPowerDTO desiredPowerDTO = new DesiredPowerDTO(powerRepository.getDesiredPower());

        return desiredPowerDTO;
    }

    public Map<String, Double> getLevels() {
        return oxygenRepository.getSupplyLevels();
    }

    public PowerDTO getCurrentPower() {
        return new PowerDTO(powerRepository.getCurrentPower());
    }

    public void requirePower() {
        // How much oxygen do we need?
        double requiredOxygen = powerRepository.getDesiredPower() / POWER_TO_OXYGEN_RATIO;

        // Fetch it from it's repository
        OxygenObtainedDTO oxygenObtainedDTO = oxygenRepository.requireOxygen(requiredOxygen);

        // Keep track of available levels
        oxygenRepository.updateLevel(oxygenObtainedDTO.getHostName(), oxygenObtainedDTO.getOxygenSupplyDTO().getLevel());

        // Calculate obtained power
        double currentPower = oxygenObtainedDTO.getAmount() * POWER_TO_OXYGEN_RATIO;

        // Update obtained power
        powerRepository.setCurrentPower(currentPower);
    }
}
