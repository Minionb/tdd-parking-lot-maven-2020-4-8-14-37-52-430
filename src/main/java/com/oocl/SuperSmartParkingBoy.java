package com.oocl;

import java.util.Comparator;

public class SuperSmartParkingBoy extends ParkingBoy {
    public SuperSmartParkingBoy(ParkingLot... parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingTicket park(Car car) throws NotEnoughPositionException {
        ParkingLot parkingLotSelection = getParkingLots().stream().max(Comparator.comparing(parkingLot -> parkingLot.getAvailablePosition())).get();
        if(parkingLotSelection == null){
            throw new NotEnoughPositionException();
        }
        return parkingLotSelection.park(car);
    }

}
