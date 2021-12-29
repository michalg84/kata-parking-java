package victor.kata.parking;

import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

import static java.util.Collections.unmodifiableList;

/**
 * Handles the parking mechanisms: park/unpark a car (also for disabled-only bays) and provides a string representation of its state.
 */
public class Parking {
    public static final int FULL = -1;
    public static final int FULL_DISABLED = -2;

    private final List<Integer> bays;
    private final List<Integer> disabledBays;
    private final List<Integer> pedestrianExits;
    private final List<Integer> parked = new ArrayList<>();


    Parking(List<Integer> bays, List<Integer> disabledBays, List<Integer> pedestrianExits) {
        if (pedestrianExits.isEmpty()) {
            throw new IllegalArgumentException("Pedestrians exits are required");
        }
        this.bays = unmodifiableList(bays);
        this.disabledBays = unmodifiableList(disabledBays);
        this.pedestrianExits = unmodifiableList(pedestrianExits);
    }

    /**
     * @return the number of available parking bays left
     */
    int getAvailableBaysCount() {
        return bays.size() - pedestrianExits.size() - parked.size();
    }

    /**
     * Park the car of the given type ('D' being dedicated to disabled people) in closest
     * -to pedestrian exit- and first (starting from the parking's entrance)
     * available bay. Disabled people can only park on dedicated bays.
     *
     * @param carType the car char representation that has to be parked
     * @return bay index of the parked car, -1 if no applicable bay found
     */
    public int parkCar(final char carType) {
        Integer placeToParkIn = null;
        if ('D' == carType) {
            placeToParkIn = parkDisabledCar();
        }

        return placeToParkIn == null ? parkRegularCar() : placeToParkIn;
    }

    private Integer parkDisabledCar() {
        var freeDisabledBays = getFreeDisabledBays();
        var distancesToExit = getDistancesToExit(freeDisabledBays);
        var min = minDistance(distancesToExit);
        if (min.isPresent()) {

            var placeToParkIn = freeDisabledBays.get(distancesToExit.indexOf(min.getAsInt()));
            parked.add(placeToParkIn);
            return placeToParkIn;

        }

        return null;
    }

    private int parkRegularCar() {
        var freeBays = getFreeBays();
        var distancesToExit = getDistancesToExit(freeBays);
        var min = minDistance(distancesToExit);
        if (min.isPresent()) {

            var placeToParkIn = freeBays.get(distancesToExit.indexOf(min.getAsInt()));
            parked.add(placeToParkIn);
            return placeToParkIn;

        }

        return FULL;
    }

    private List<Integer> getFreeDisabledBays() {
        return disabledBays.stream()
                .filter(bay -> !parked.contains(bay))
                .toList();
    }

    private List<Integer> getFreeBays() {
        return bays.stream()
                .filter(bay -> !pedestrianExits.contains(bay))
                .filter(bay -> !parked.contains(bay))
                .toList();
    }

    private OptionalInt minDistance(List<Integer> distancesToExit) {
        return distancesToExit.stream().mapToInt(x -> x)
                .min();
    }

    private List<Integer> getDistancesToExit(List<Integer> freeBays) {
        return freeBays.stream()
                .map(this::distanceToExit).toList();
    }


    private int distanceToExit(int bay) {
        return Math.abs(pedestrianExits.get(0) - bay);
    }

    /**
     * Unpark the car from the given index
     *
     * @param index
     * @return true if a car was parked in the bay, false otherwise
     */
    public boolean unparkCar(final int index) {
        return parked.remove(Integer.valueOf(index));
    }

    public List<Integer> getDisabledBays() {
        return disabledBays;
    }

    public List<Integer> getPedestrianExits() {
        return pedestrianExits;
    }

    /**
     * Print a 2-dimensional representation of the parking with the following rules:
     * <ul>
     * <li>'=' is a pedestrian exit
     * <li>'@' is a disabled-only empty bay
     * <li>'U' is a non-disabled empty bay
     * <li>'D' is a disabled-only occupied bay
     * <li>the char representation of a parked vehicle for non-empty bays.
     * </ul>
     * U, D, @ and = can be considered as reserved chars.
     * <p>
     * Once an end of lane is reached, then the next lane is reversed (to represent the fact that cars need to turn around)
     *
     * @return the string representation of the parking as a 2-dimensional square. Note that cars do a U turn to continue to the next lane.
     */
    @Override
    public String toString() {
        throw new NotImplementedException("TODO");
    }
}
