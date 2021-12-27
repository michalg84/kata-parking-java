package victor.kata.parking;

import java.util.List;

class ParkingPrinter {
    final List<String> bays;
    final int laneLength;

    public ParkingPrinter(List<String> bays) {
        this.bays = bays;
        laneLength = (int) Math.sqrt(bays.size());
    }

    public String toString() {
        final StringBuilder parkingBuilder = new StringBuilder();
        StringBuilder lineBuilder = new StringBuilder();
        int index = 0;
        for (String bay : bays) {
            lineBuilder.append(bay);
            index++;
            if (isEndOfLine(index)) {
                final String line = handleLineEndAndCreation(lineBuilder, laneLength, index);
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

    private String handleLineEndAndCreation(StringBuilder builder, Integer laneLength, Integer index) {
        if (isOddRow(laneLength, index)) {
            builder.reverse();
        }
        final StringBuilder lineBuilder = builder;
        if (!isEndOfParking(laneLength, index)) {
            lineBuilder.append("\n");
        }
        return lineBuilder.toString();
    }

    private boolean isEndOfParking(Integer laneLength, Integer index) {
        return laneLength * laneLength == index;
    }

    private boolean isOddRow(Integer laneLength, Integer index) {
        return index / laneLength % 2 == 0;
    }
}
