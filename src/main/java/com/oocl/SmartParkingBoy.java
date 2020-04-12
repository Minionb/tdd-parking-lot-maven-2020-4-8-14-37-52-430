package com.oocl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy{
    public SmartParkingBoy(ParkingLot... parkingLot) {
        super(parkingLot);
    }

    @Override
    public ParkingTicket park(Car car) throws NotEnoughPositionException {

        ParkingLot parkingLotSelection = getParkingLots().stream().max(Comparator.comparing(parkingLot -> parkingLot.getEmptyPosition())).get();

        if(parkingLotSelection == null){
            throw new NotEnoughPositionException();
        }
        return parkingLotSelection.park(car);
    }
}
