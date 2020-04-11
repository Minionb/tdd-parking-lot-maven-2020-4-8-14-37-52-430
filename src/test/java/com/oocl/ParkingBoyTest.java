package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ParkingBoyTest {
    private ParkingLot parkingLot;
    @Before
    public void setUp() throws Exception{
        parkingLot = new ParkingLot();

    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void should_return_parking_ticket_when_parking_boy_park_car() {
        ParkingTicket parkingTicket = parkingLot.park(new Car());
        Assert.assertNotNull(parkingTicket);

    }
    @Test
    public void should_return_car_when_parking_boy_fetch_car_with_parking_ticket() {
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);

        Car fetchedCarFromParkingLot = parkingLot.fetch(parkingTicket);

        assertEquals(car,fetchedCarFromParkingLot);

    }

}