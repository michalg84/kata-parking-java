package victor.kata.parking;

import org.apache.commons.lang3.NotImplementedException;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the parking mechanisms: park/unpark a car (also for disabled-only bays) and provides a string representation of its state.
 */
public class Parking {

    public static final String EXIT = "=";
    public static final String DISABLED_BAY = "@";
    public static final String EMPTY_BAY = "U";
    public static final String OCCUPIED_BAY = "D";
    private Integer laneLength;
    private final List<String> bays;


    Parking(int laneLength, List<String> bays) {
        this.laneLength = laneLength;
        this.bays = bays;
    }

    /**
     * @return the number of available parking bays left
     */
    public int getBaysCount() {
        return bays.size() - (int) bays.stream().filter(EXIT::equals).count();
    }

    /**
     * Park the car of the given type ('D' being dedicated to disabled people) in closest -to pedestrian exit- and first (starting from the parking's entrance)
     * available bay. Disabled people can only park on dedicated bays.
     *
     * @param carType the car char representation that has to be parked
     * @return bay index of the parked car, -1 if no applicable bay found
     */
    public int parkCar(final char carType) {
        throw new NotImplementedException("TODO");
    }

    /**
     * Unpark the car from the given index
     *
     * @param index
     * @return true if a car was parked in the bay, false otherwise
     */
    public boolean unparkCar(final int index) {
        throw new NotImplementedException("TODO");
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
        final StringBuilder builder = new StringBuilder();
        int index = 0;
        for (String bay : bays) {
            if (index > 0 && index % laneLength == 0) {
                builder.append(System.lineSeparator());
            }
            builder.append(bay);
            index++;
        }
        return builder.toString();
    }

    List<Integer> getIndexes(String disabledBay) {
        List<Integer> list = new ArrayList<>();
        int i = 0;
        while (i < bays.size()) {
            String availableBay = bays.get(i);
            if (disabledBay.equals(availableBay)) {
                list.add(i);
            }
            i++;
        }
        return list;
    }
}
