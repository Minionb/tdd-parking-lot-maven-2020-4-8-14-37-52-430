package com.oocl.structure;

import com.oocl.exception.NotEnoughPositionException;

import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLot... parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingTicket park(Car car) throws NotEnoughPositionException {

        ParkingLot parkingLotSelection = getParkingLots().stream().max(Comparator.comparing(ParkingLot::getEmptyPosition)).orElseThrow(NotEnoughPositionException::new);

        return parkingLotSelection.park(car);
    }
}
