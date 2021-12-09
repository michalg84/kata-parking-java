package victor.kata.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Builder class to get a parking instance
 */
public class ParkingBuilder {
    private List<Integer> availableBays;
    private final List<Integer> pedestrianExits = new ArrayList<>();

    public ParkingBuilder withSquareSize(final int side) {
        availableBays = IntStream.generate(() -> 0)
                .limit((long) side * side)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        return this;
    }

    public ParkingBuilder withPedestrianExit(final int pedestrianExitIndex) {
        this.pedestrianExits.add(pedestrianExitIndex);
        return this;
    }

    public ParkingBuilder withDisabledBay(final int disabledBayIndex) {
        return this;
    }

    public Parking build() {
        return new Parking(availableBays, pedestrianExits);
    }
}
