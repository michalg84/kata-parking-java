package victor.kata.parking;

import org.apache.commons.lang3.NotImplementedException;

/**
 * Builder class to get a parking instance
 */
public class ParkingBuilder {
    private int availableBays;

    public ParkingBuilder withSquareSize(final int size) {
        availableBays = size * size;
        return this;
    }

    public ParkingBuilder withPedestrianExit(final int pedestrianExitIndex) {
        throw new NotImplementedException("TODO");
    }

    public ParkingBuilder withDisabledBay(final int disabledBayIndex) {
        throw new NotImplementedException("TODO");
    }

    public Parking build() {

        return new Parking(availableBays);
    }
}
