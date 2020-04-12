package com.oocl;

import com.oocl.exception.NoTicketException;
import com.oocl.exception.NotEnoughPositionException;
import com.oocl.exception.UnrecognizedParkingTicketException;
import com.oocl.structure.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class ParkingBoyTest {
    private ParkingLot parkingLot;

    @Before
    public void setUp() throws Exception {
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

        assertEquals(car, fetchedCarFromParkingLot);

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

    @Test
    public void should_park_to_second_parking_lot_when_the_first_parking_lot_is_full() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(firstParkingLot, secondParkingLot);
        parkingBoy.park(new Car());
        Car car = new Car();
        ParkingTicket parkingTicket = parkingBoy.park(car);
        Car fetchedCar = secondParkingLot.fetch(parkingTicket);
        assertEquals(car, fetchedCar);
    }

    @Test
    public void should_smart_parking_boy_park_car_to_parking_lot_with_more_space_left() {
        ParkingLot firstParkingLot = new ParkingLot(8);
        ParkingLot secondParkingLot = new ParkingLot(10);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(firstParkingLot, secondParkingLot);

        Car car = new Car();
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        Car fetchedCar = secondParkingLot.fetch(parkingTicket);
        assertEquals(car, fetchedCar);
    }

    @Test
    public void should_super_smart_parking_boy_park_car_to_parking_lot_with_larger_available_position() {
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(firstParkingLot, secondParkingLot);

        for (int index = 0; index < 4; index++) {
            firstParkingLot.park(new Car());
        }
        Car car = new Car();
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);
        Car fetchedCar = secondParkingLot.fetch(parkingTicket);
        assertEquals(car, fetchedCar);
    }
}