package be.xploregroup.joe.himself.configuration;

import be.xploregroup.joe.oxygen.OxygenSystemType;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Created by bertswinnen on 18/10/15.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "oxygen")
public class OxygenConfiguration {
    private Map<String, String> reductionUriMap;
    private OxygenSystemType oxygenSystemType;

    public String getReductionUri(OxygenSystemType oxygenSystemType) {
        return reductionUriMap.get(oxygenSystemType.getValue());
    }

    public Map<String, String> getReductionUriMap() {
        return reductionUriMap;
    }

    public void setReductionUriMap(Map<String, String> reductionUriMap) {
        this.reductionUriMap = reductionUriMap;
    }

    public OxygenSystemType getOxygenSystemType() {
        return oxygenSystemType;
    }

    public void setType(String type) {
        this.oxygenSystemType = OxygenSystemType.getByType(type);
    }
}
