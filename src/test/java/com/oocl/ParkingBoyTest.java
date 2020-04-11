package com.oocl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import sun.security.krb5.internal.Ticket;

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

    @Test
    public void should_throw_exception_when_wrong_ticket() {
        expectedException.expect(UnrecognizedParkingTicketException.class);
        expectedException.expectMessage("Unrecognized parking ticket.");
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        parkingBoy.park(car);
        ParkingTicket incorrectTicket = new ParkingTicket();
        Car fetchedCar = parkingBoy.fetch(incorrectTicket);
        assertNull(fetchedCar);


    }
    @Test
    public void should_throw_exception_when_no_ticket_provided() {
        expectedException.expect(NoTicketException.class);
        expectedException.expectMessage("Please provide your parking ticket.");
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        ParkingTicket noTicket = null;
        Car fetchedCar = parkingBoy.fetch(noTicket);
        assertNull(fetchedCar);

    }

    @Test
    public void should_throw_exception_when_parking_lot_full() {
        expectedException.expect(NotEnoughPositionException.class);
        expectedException.expectMessage("Not enough position.");
        parkingLot.setCapacity(1);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Car car = new Car();
        parkingBoy.park(car);
        ParkingTicket parkingTicket = parkingLot.park(new Car());

        assertNull(parkingTicket);

    }

}