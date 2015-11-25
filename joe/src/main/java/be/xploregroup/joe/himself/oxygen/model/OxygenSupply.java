package be.xploregroup.joe.himself.oxygen.model;

import org.apache.commons.math3.analysis.function.Pow;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bertswinnen on 18/10/15.
 */
public class OxygenSupply {
    private Map<String, Double> levelsMap = new HashMap<>();

    public OxygenSupply(){

    }

    public void updateLevel(String source, Double level){
        if(!"-".equalsIgnoreCase(source)){
            levelsMap.put(source, level);
        }
    }

    public Map<String, Double> getLevels(){
        return levelsMap;
    }
}
