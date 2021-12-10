package victor.kata.parking;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParkingBuilderTest {

    @Test
    public void testBuildBasicParking() {
        final Parking parking = new ParkingBuilder().withSquareSize(4).build();
        assertEquals(16, parking.getAvailableBays());
    }

    @Test
    public void testBuildParkingWithPedestrianExit() {
        final Parking parking = new ParkingBuilder()
                .withSquareSize(3)
                .withPedestrianExit(5)
                .build();
        assertEquals(8, parking.getAvailableBays());
        assertEquals(1, parking.getPedestrianExitIndexes().size());
        assertEquals(5, parking.getPedestrianExitIndexes().get(0).intValue());
    }

    @Test
    public void testBuildParkingWithDisabledSlot() {
        final Parking parking = new ParkingBuilder().withSquareSize(2).withDisabledBay(2).build();
        assertEquals(4, parking.getAvailableBays());
        assertEquals(1, parking.getDisabledBayIndexes().size());
        assertEquals(2, parking.getDisabledBayIndexes().get(0).intValue());
    }

    @Test
    public void testBuildParkingWithPedestrianExitsAndDisabledSlots() {
        final Parking parking = new ParkingBuilder()
                .withSquareSize(10)
                .withPedestrianExit(8)
                .withPedestrianExit(42)
                .withPedestrianExit(85)
                .withDisabledBay(2)
                .withDisabledBay(47)
                .withDisabledBay(72)
                .build();
        assertEquals(97, parking.getAvailableBays());
        assertEquals(3, parking.getDisabledBayIndexes().size());
        assertEquals(2, parking.getDisabledBayIndexes().get(0).intValue());
        assertEquals(47, parking.getDisabledBayIndexes().get(1).intValue());
        assertEquals(72, parking.getDisabledBayIndexes().get(2).intValue());
        assertEquals(3, parking.getPedestrianExitIndexes().size());
        assertEquals(8, parking.getPedestrianExitIndexes().get(0).intValue());
        assertEquals(42, parking.getPedestrianExitIndexes().get(1).intValue());
        assertEquals(85, parking.getPedestrianExitIndexes().get(2).intValue());
    }
}
