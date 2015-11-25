package be.xploregroup.joe.himself.oxygen.repository;

import be.xploregroup.joe.himself.configuration.OxygenConfiguration;
import be.xploregroup.joe.himself.oxygen.model.OxygenSupply;
import be.xploregroup.joe.himself.oxygen.command.ReduceOxygenCommand;
import be.xploregroup.joe.oxygen.dto.OxygenObtainedDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * Created by bertswinnen on 07/11/15.
 */
@Repository
public class OxygenRepository {
    @Autowired
    private OxygenConfiguration oxygenConfiguration;

    private OxygenSupply oxygenSupply;
    private static Logger logger = LoggerFactory.getLogger(OxygenRepository.class);

    private OxygenRepository() {
    }

    @PostConstruct
    public void init() {
        this.oxygenSupply = new OxygenSupply();
    }

    public Map<String, Double> getSupplyLevels() {
        return this.oxygenSupply.getLevels();
    }

    public void updateLevel(String source, Double level) {
        this.oxygenSupply.updateLevel(source, level);
    }

    public OxygenObtainedDTO requireOxygen(double requiredOxygen) {
        logger.debug("Required oxygen:" + requiredOxygen);

        ReduceOxygenCommand reduceOxygenCommand = new ReduceOxygenCommand(requiredOxygen, oxygenConfiguration);
        OxygenObtainedDTO oxygenObtainedDTO = reduceOxygenCommand.execute();

        //OxygenObtainedDTO oxygenObtainedDTO = fetch(requiredOxygen);

        logger.debug("Obtained oxygen:" + oxygenObtainedDTO.getAmount());

        return oxygenObtainedDTO;
    }

    /**
    protected OxygenObtainedDTO fetch(double reduction) {
        String oxygenReductionUri = oxygenConfiguration.getReductionUri(oxygenConfiguration.getOxygenSystemType());

        OxygenObtainedDTO oxygenObtainedDTO = obtain(oxygenReductionUri, reduction);

        return oxygenObtainedDTO;
    }

    private OxygenObtainedDTO obtain(String reductionUri, double reduction) {
        OxygenReductionDTO oxygenReductionDTO = new OxygenReductionDTO(reduction);

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<OxygenReductionDTO> requestEntity = new HttpEntity<OxygenReductionDTO>(oxygenReductionDTO, httpHeaders);

        try {
            RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
            ResponseEntity<OxygenObtainedDTO> responseEntity = restTemplate.exchange(reductionUri, HttpMethod.POST, requestEntity, OxygenObtainedDTO.class);

            OxygenObtainedDTO oxygenObtainedDTO = responseEntity.getBody();

            return oxygenObtainedDTO;
        } catch (Exception e) {
            logger.debug("failed.. ", e);

            return getFallbackOxygenObtainedDTO();
        }
    }

    private OxygenObtainedDTO getFallbackOxygenObtainedDTO() {
        OxygenSupplyDTO oxygenSupplyDTO = new OxygenSupplyDTO();
        oxygenSupplyDTO.setHostName("-");
        oxygenSupplyDTO.setHostIp("-");
        oxygenSupplyDTO.setLevel(-1);

        OxygenObtainedDTO oxygenObtainedDTO = new OxygenObtainedDTO(0);
        oxygenObtainedDTO.setOxygenSupplyDTO(oxygenSupplyDTO);
        return oxygenObtainedDTO;
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setReadTimeout(1000);
        factory.setConnectTimeout(1000);
        return factory;
    }*/
}
