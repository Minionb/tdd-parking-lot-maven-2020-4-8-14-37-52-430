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
        ParkingLot parkingLotSelection = getParkingLots().stream().max(Comparator.comparing(parkingLot -> parkingLot.getAvailablePosition())).get();
        if (parkingLotSelection == null) {
            throw new NotEnoughPositionException();
        }
        return parkingLotSelection.park(car);
    }

    @Override
    public ParkingLot selectLot() {
        ParkingLot parkingLotSelection = getParkingLots().stream().max(Comparator.comparing(parkingLot -> parkingLot.getAvailablePosition())).get();
        return parkingLotSelection;
    }

    @Override
    public Car fetch(ParkingTicket parkingTicket) throws UnrecognizedParkingTicketException, NotEnoughPositionException {
        return selectLot().fetch(parkingTicket);
    }
}
