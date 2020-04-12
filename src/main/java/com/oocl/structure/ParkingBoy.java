package com.oocl.structure;

import com.oocl.exception.NotEnoughPositionException;
import com.oocl.exception.UnrecognizedParkingTicketException;

import java.util.*;

public class ParkingBoy {
    private List<ParkingLot> parkingLots = new ArrayList<>();

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }


    public ParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots.addAll((Arrays.asList(parkingLots)));
    }


    public ParkingTicket park(Car car) throws NotEnoughPositionException {
        ParkingLot parkingLotSelection = this.parkingLots.stream().filter(parkingLot -> !parkingLot.isFull()).findFirst().orElse(null);

        if (parkingLotSelection == null) {
            throw new NotEnoughPositionException();
        }
        return parkingLotSelection.park(car);
    }


    public Car fetch(ParkingTicket parkingTicket) throws UnrecognizedParkingTicketException, NotEnoughPositionException {

        return this.parkingLots.get(0).fetch(parkingTicket);
    }

}
