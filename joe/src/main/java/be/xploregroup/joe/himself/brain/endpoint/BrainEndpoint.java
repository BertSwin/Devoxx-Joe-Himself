package be.xploregroup.joe.himself.brain.endpoint;

import be.xploregroup.joe.himself.brain.dto.RequestedPaceDTO;
import be.xploregroup.joe.himself.brain.service.BrainService;
import be.xploregroup.joe.himself.power.dto.DesiredPowerDTO;
import com.wordnik.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.GaugeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by bertswinnen on 26/09/15.
 */
@Api(value = "/", description = "Brain endpoint")
@RestController
@RequestMapping("himself/brain")
public class BrainEndpoint {

    @Autowired
    private GaugeService gauges;

    @Autowired
    private BrainService brainService;

    @RequestMapping(method = RequestMethod.POST, value = "/pace")
    @ResponseBody
    public ResponseEntity<DesiredPowerDTO> requestPace(@RequestBody RequestedPaceDTO requestedPaceDTO) {
        // Sets the
        DesiredPowerDTO desiredPowerDTO = brainService.requestPace(requestedPaceDTO);

        return new ResponseEntity<DesiredPowerDTO>(
                desiredPowerDTO, new HttpHeaders(),
                HttpStatus.OK);
    }
}
