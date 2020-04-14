package com.oocl.structure;

import com.oocl.exception.NotEnoughPositionException;
import com.oocl.exception.UnrecognizedParkingTicketException;

import java.util.Comparator;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(ParkingLot... parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingTicket park(Car car) throws NotEnoughPositionException {
        ParkingLot parkingLotSelection = getParkingLots().stream().max(Comparator.comparing(ParkingLot::getRemainingRatio)).orElseThrow(NotEnoughPositionException::new);

        return parkingLotSelection.park(car);
    }

    @Override
    public ParkingLot selectLot() {
        return getParkingLots().stream().max(Comparator.comparing(ParkingLot::getRemainingRatio)).orElseThrow(NotEnoughPositionException::new);
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket) throws UnrecognizedParkingTicketException, NotEnoughPositionException {
        return selectLot().fetch(parkingTicket);
    }
}
