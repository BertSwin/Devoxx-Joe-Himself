package be.xploregroup.joe.himself.brain.service;

import org.apache.commons.math3.util.Precision;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Created by bertswinnen on 13/10/15.
 */
public class BrainServiceTest {
    private BrainService brainService = new BrainService();

    @Test
    public void test_given50Kph_expected601Watts() {
        double watts = brainService.calculatePower(50);
        watts = Precision.round(watts, 0);
        assertThat(watts).isEqualTo(Precision.round(601, 0));
    }

    @Test
    public void test_given45Kph_expected448Watts() {
        double watts = brainService.calculatePower(45);
        watts = Precision.round(watts, 0);
        assertThat(watts).isEqualTo(Precision.round(448, 0));
    }

    @Test
    public void test_given40Kph_expected324Watts() {
        double watts = brainService.calculatePower(40);
        watts = Precision.round(watts, 0);
        assertThat(watts).isEqualTo(Precision.round(325, 0));
    }

    @Test
    public void test_given35Kph_expected227Watts() {
        double watts = brainService.calculatePower(35);
        watts = Precision.round(watts, 0);
        assertThat(watts).isEqualTo(Precision.round(227, 0));
    }

    @Test
    public void test_given30Kph_expected152Watts() {
        double watts = brainService.calculatePower(30);
        watts = Precision.round(watts, 0);
        assertThat(watts).isEqualTo(Precision.round(152, 0));
    }

    @Test
    public void test_given25Kph_expected71Watts() {
        double watts = brainService.calculatePower(25);
        watts = Precision.round(watts, 0);
        assertThat(watts).isEqualTo(Precision.round(97, 0));
    }

    @Test
    public void test_given20Kph_expected58Watts() {
        double watts = brainService.calculatePower(20);
        watts = Precision.round(watts, 0);
        assertThat(watts).isEqualTo(Precision.round(58, 0));
    }

    @Test
    public void test_given15Kph_expected30Watts() {
        double watts = brainService.calculatePower(15);
        watts = Precision.round(watts, 0);
        assertThat(watts).isEqualTo(Precision.round(32, 0));
    }

    @Test
    public void test_given10Kph_expected15Watts() {
        double watts = brainService.calculatePower(10);
        watts = Precision.round(watts, 0);
        assertThat(watts).isEqualTo(Precision.round(16, 0));
    }

    @Test
    public void test_given5Kph_expected3Watts() {
        double watts = brainService.calculatePower(5);
        watts = Precision.round(watts, 0);
        assertThat(watts).isEqualTo(Precision.round(6, 0));
    }

    @Test
    public void test_given0Kph_expected0Watts() {
        double watts = brainService.calculatePower(0);
        watts = Precision.round(watts, 0);
        assertThat(watts).isEqualTo(Precision.round(0, 0));
    }
}
