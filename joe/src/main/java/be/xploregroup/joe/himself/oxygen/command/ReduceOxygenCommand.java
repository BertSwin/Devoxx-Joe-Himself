package be.xploregroup.joe.himself.oxygen.command;

import be.xploregroup.joe.himself.configuration.OxygenConfiguration;
import be.xploregroup.joe.oxygen.dto.OxygenObtainedDTO;
import be.xploregroup.joe.oxygen.dto.OxygenReductionDTO;
import be.xploregroup.joe.oxygen.dto.OxygenSupplyDTO;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * Created by bertswinnen on 18/10/15.
 */
public class ReduceOxygenCommand extends HystrixCommand<OxygenObtainedDTO> {
    private static Logger logger = LoggerFactory.getLogger(ReduceOxygenCommand.class);

    private OxygenConfiguration oxygenConfiguration;
    private double reduction;

    public ReduceOxygenCommand(double reduction, OxygenConfiguration oxygenConfiguration) {
        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("Oxygen"))
                        .andCommandPropertiesDefaults(
                                HystrixCommandProperties.Setter()
                                        .withCircuitBreakerSleepWindowInMilliseconds(100)));

        this.reduction = reduction;
        this.oxygenConfiguration = oxygenConfiguration;
    }

    @Override
    protected OxygenObtainedDTO run() throws Exception {
        logger.debug("Run::Circuit is open?:" + this.isCircuitBreakerOpen());

        String oxygenReductionUri = oxygenConfiguration.getReductionUri(oxygenConfiguration.getOxygenSystemType());

        OxygenObtainedDTO oxygenObtainedDTO = null;
        if (reduction != 0) {
            oxygenObtainedDTO = obtain(oxygenReductionUri);
        } else {
            oxygenObtainedDTO = getFallbackOxygenObtainedDTO();
        }

        return oxygenObtainedDTO;
    }

    @Override
    protected OxygenObtainedDTO getFallback() {
        logger.debug("Fallback::Circuit is open?:" + this.isCircuitBreakerOpen());

        return getFallbackOxygenObtainedDTO();
    }

    private OxygenObtainedDTO obtain(String reductionUri) {
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
            throw e;
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
    }
}
