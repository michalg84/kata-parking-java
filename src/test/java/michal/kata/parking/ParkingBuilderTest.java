package michal.kata.parking;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ParkingBuilderTest {

    @Test
    public void testBuildParkingWithoutExits() {
        final ParkingBuilder parkingBuilder = new ParkingBuilder().withSquareSize(4);
        assertThrows("Pedestrians exits are required",
                IllegalArgumentException.class,
                parkingBuilder::build);
    }

    @Test
    public void testBuildParkingWithPedestrianExit() {
        final Parking parking = new ParkingBuilder()
                .withSquareSize(3)
                .withPedestrianExit(5)
                .build();
        assertEquals(8, parking.getAvailableBaysCount());
        assertEquals(1, parking.getPedestrianExits().size());
        assertEquals(5, parking.getPedestrianExits().get(0).intValue());
    }

    @Test
    public void testBuildParkingWithDisabledSlot() {
        final Parking parking = new ParkingBuilder()
                .withSquareSize(2)
                .withDisabledBay(2)
                .withPedestrianExit(1)
                .build();
        assertEquals(3, parking.getAvailableBaysCount());
        assertEquals(1, parking.getDisabledBays().size());
        assertEquals(2, parking.getDisabledBays().get(0).intValue());
    }

    @Test
    public void testBuildParkingWithMultiplePedestrianExitsAndDisabledSlots() {
        final Parking parking = new ParkingBuilder()
                .withSquareSize(10)
                .withPedestrianExit(8)
                .withPedestrianExit(42)
                .withPedestrianExit(85)
                .withDisabledBay(2)
                .withDisabledBay(47)
                .withDisabledBay(72)
                .build();
        assertEquals(97, parking.getAvailableBaysCount());
        assertEquals(3, parking.getDisabledBays().size());
        assertEquals(2, parking.getDisabledBays().get(0).intValue());
        assertEquals(47, parking.getDisabledBays().get(1).intValue());
        assertEquals(72, parking.getDisabledBays().get(2).intValue());
        assertEquals(3, parking.getPedestrianExits().size());
        assertEquals(8, parking.getPedestrianExits().get(0).intValue());
        assertEquals(42, parking.getPedestrianExits().get(1).intValue());
        assertEquals(85, parking.getPedestrianExits().get(2).intValue());
    }
}
