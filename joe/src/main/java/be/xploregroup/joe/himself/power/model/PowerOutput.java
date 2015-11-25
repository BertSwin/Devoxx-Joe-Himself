package be.xploregroup.joe.himself.power.model;

/**
 * Created by bertswinnen on 18/10/15.
 */
public class PowerOutput {
    private double desiredPower = 0;
    private double currentPower = 0;

    public double getCurrentPower() {
        return currentPower;
    }

    public void setCurrentPower(double currentPower) {
        this.currentPower = currentPower;
    }

    public double getDesiredPower() {
        return desiredPower;
    }

    public void setDesiredPower(double desiredPower) {
        this.desiredPower = desiredPower;
    }
}
