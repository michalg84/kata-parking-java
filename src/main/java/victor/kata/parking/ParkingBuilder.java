package victor.kata.parking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Collections.unmodifiableList;

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
        final ArrayList<Integer> availableBays = IntStream.generate(() -> 0)
                .limit(size)
                .boxed()
                .collect(Collectors.toCollection(ArrayList::new));
        return new Parking(availableBays,
                unmodifiableList(pedestrianExits),
                unmodifiableList(disabledBay));
    }
}
