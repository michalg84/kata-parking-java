package victor.kata.parking;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ParkingBuilderTest {

    @Test
    public void testBuildBasicParking() {
        final Parking parking = new ParkingBuilder().withSquareSize(4).build();
        assertEquals(16, parking.getBaysCount());
    }

    @Test
    public void testBuildParkingWithPedestrianExit() {
        final Parking parking = new ParkingBuilder()
                .withSquareSize(3)
                .withPedestrianExit(5)
                .build();
        assertEquals(8, parking.getBaysCount());
        assertEquals(1, parking.getIndexes(Parking.EXIT).size());
        assertEquals(5, parking.getIndexes(Parking.EXIT).get(0).intValue());
    }

    @Test
    public void testBuildParkingWithDisabledSlot() {
        final Parking parking = new ParkingBuilder().withSquareSize(2).withDisabledBay(2).build();
        assertEquals(4, parking.getBaysCount());
        assertEquals(1, parking.getIndexes(Parking.DISABLED_BAY).size());
        assertEquals(2, parking.getIndexes(Parking.DISABLED_BAY).get(0).intValue());
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
        assertEquals(97, parking.getBaysCount());
        assertEquals(3, parking.getIndexes(Parking.DISABLED_BAY).size());
        assertEquals(2, parking.getIndexes(Parking.DISABLED_BAY).get(0).intValue());
        assertEquals(47, parking.getIndexes(Parking.DISABLED_BAY).get(1).intValue());
        assertEquals(72, parking.getIndexes(Parking.DISABLED_BAY).get(2).intValue());
        assertEquals(3, parking.getIndexes(Parking.EXIT).size());
        assertEquals(8, parking.getIndexes(Parking.EXIT).get(0).intValue());
        assertEquals(42, parking.getIndexes(Parking.EXIT).get(1).intValue());
        assertEquals(85, parking.getIndexes(Parking.EXIT).get(2).intValue());
    }
}
