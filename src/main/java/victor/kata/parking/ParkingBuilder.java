package victor.kata.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static victor.kata.parking.Parking.*;

/**
 * Builder class to get a parking instance
 */
public class ParkingBuilder {
    private Integer size;
    private final List<Integer> disabledBay = new ArrayList<>();
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
        disabledBay.add(index);
        return this;
    }

    public Parking build() {
        final List<String> availableBays = Stream.generate(() -> EMPTY_BAY)
                .limit(size)
                .collect(Collectors.toCollection(ArrayList::new));
        pedestrianExits.forEach(pe -> availableBays.set(pe, EXIT));
        disabledBay.forEach(db -> availableBays.set(db, DISABLED_BAY));
        return new Parking(availableBays);
    }
}
