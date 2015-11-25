package be.xploregroup.joe.himself.oxygen.endpoint;

import be.xploregroup.joe.himself.oxygen.service.OxygenService;
import be.xploregroup.joe.himself.power.dto.DesiredPowerDTO;
import be.xploregroup.joe.himself.power.dto.PowerDTO;
import be.xploregroup.joe.himself.power.service.PowerService;
import com.wordnik.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by bertswinnen on 26/09/15.
 */
@Api(value = "/", description = "Oxygen endpoint")
@RestController
@RequestMapping("himself/oxygen")
public class OxygenEndpoint {
    @Autowired
    private OxygenService oxygenService;


    @RequestMapping(method = RequestMethod.GET, value = "/levels")
    @ResponseBody
    public ResponseEntity<Map<String,Double>> getSupplyLevels() {
        Map<String,Double> supplyLevels = oxygenService.getSupplyLevels();

        return new ResponseEntity<Map<String,Double>>(
                supplyLevels, new HttpHeaders(),
                HttpStatus.OK);
    }
}
