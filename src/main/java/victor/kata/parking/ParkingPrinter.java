package victor.kata.parking;

import java.util.List;

class ParkingPrinter {
    public static final String EXIT = "=";
    public static final String DISABLED_BAY = "@";
    public static final String EMPTY_BAY = "U";
    public static final String OCCUPIED_BAY = "D";
    private final List<String> bays;
    private final int laneLength;
    private final StringBuilder parkingBuilder = new StringBuilder();
    private final StringBuilder lineBuilder = new StringBuilder();

    public ParkingPrinter(List<String> bays) {
        this.bays = bays;
        laneLength = (int) Math.sqrt(bays.size());
    }

    public String print() {
        int bayIndex = 0;
        for (String bay : bays) {
            lineBuilder.append(bay);
            bayIndex++;
            if (isEndOfLine(bayIndex)) {
                if (isOddLine(laneLength, bayIndex)) {
                    lineBuilder.reverse();
                }
                lineBuilder.append("\n");
                if (isEndOfParking(laneLength, bayIndex)) {
                    lineBuilder.deleteCharAt(lineBuilder.length() - 1);
                }
                String line = lineBuilder.toString();
                clearContentOf(lineBuilder);
                parkingBuilder.append(line);
            }
        }
        return parkingBuilder.toString();
    }

    private void clearContentOf(StringBuilder lineBuilder) {
        lineBuilder.delete(0, lineBuilder.length());
    }

    private boolean isEndOfLine(int index) {
        return index % laneLength == 0;
    }

    private boolean isEndOfParking(Integer laneLength, Integer index) {
        return laneLength * laneLength == index;
    }

    private boolean isOddLine(Integer laneLength, Integer index) {
        return index / laneLength % 2 == 0;
    }
}
