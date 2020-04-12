package com.oocl.structure;

import com.oocl.exception.NotEnoughPositionException;

import java.util.Comparator;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLot... parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingTicket park(Car car) throws NotEnoughPositionException {

        ParkingLot parkingLotSelection = getParkingLots().stream().max(Comparator.comparing(parkingLot -> parkingLot.getEmptyPosition())).get();

        if (parkingLotSelection == null) {
            throw new NotEnoughPositionException();
        }
        return parkingLotSelection.park(car);
    }
}
