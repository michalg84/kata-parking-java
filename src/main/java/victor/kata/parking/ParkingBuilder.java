package victor.kata.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Builder class to get a parking instance
 */
public class ParkingBuilder {
    private int size;
    private final List<Integer> disabledBays = new ArrayList<>();
    private final List<Integer> pedestrianExits = new ArrayList<>();

    public ParkingBuilder withSquareSize(final int side) {
        this.size = side * side;
        return this;
    }

    public ParkingBuilder withPedestrianExit(final int index) {
        this.pedestrianExits.add(index);
        return this;
    }

    public ParkingBuilder withDisabledBay(final int index) {
        disabledBays.add(index);
        return this;
    }

    public Parking build() {
        final List<Integer> availableBays = IntStream.range(0, size)
                .boxed()
                .toList();
        return new Parking(availableBays, disabledBays, pedestrianExits);
    }
}
