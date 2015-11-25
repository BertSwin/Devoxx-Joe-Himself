package be.xploregroup.joe.himself.oxygen.service;

import be.xploregroup.joe.himself.oxygen.repository.OxygenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by bertswinnen on 07/11/15.
 */
@Service
public class OxygenService {
    @Autowired
    private OxygenRepository oxygenRepository;

    public Map<String, Double> getSupplyLevels(){
        return oxygenRepository.getSupplyLevels();
    }
}
