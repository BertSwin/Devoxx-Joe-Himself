package be.xploregroup.joe.himself.power.dto;

import org.apache.commons.math3.util.Precision;

/**
 * Created by bertswinnen on 13/10/15.
 */
public class PowerDTO {
    public double watts;

    public PowerDTO() {
    }

    public PowerDTO(double watts) {
        this.watts = watts;
    }

    public double getWatts() {
        return Precision.round(this.watts, 2);
    }

    public void setWatts(double watts) {
        this.watts = watts;
    }
}
