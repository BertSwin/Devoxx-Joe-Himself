package be.xploregroup.joe.himself.power.endpoint;

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

/**
 * Created by bertswinnen on 26/09/15.
 */
@Api(value = "/", description = "Power endpoint")
@RestController
@RequestMapping("himself/power")
public class PowerEndpoint {

    @Autowired
    private GaugeService gauges;

    @Autowired
    private PowerService powerService;


    @RequestMapping(method = RequestMethod.GET, value = "/requested")
    @ResponseBody
    public ResponseEntity<DesiredPowerDTO> getRequestedPowerOutput() {
        DesiredPowerDTO desiredPowerDTO = powerService.getDesiredPower();

        return new ResponseEntity<DesiredPowerDTO>(
                new DesiredPowerDTO(), new HttpHeaders(),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/current")
    @ResponseBody
    public ResponseEntity<PowerDTO> getCurrentPowerOutput() {
        PowerDTO powerDTO = powerService.getCurrentPower();

        return new ResponseEntity<PowerDTO>(
                powerDTO, new HttpHeaders(),
                HttpStatus.OK);
    }
}
