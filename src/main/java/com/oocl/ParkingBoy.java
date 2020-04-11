package com.oocl;

import java.lang.reflect.Array;
import java.util.*;

public class ParkingBoy {
    private List<ParkingLot> parkingLots = new ArrayList<>();
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot... parkingLots){
        this.parkingLots.addAll((Arrays.asList(parkingLots)));
    }


    public ParkingTicket park(Car car){
       ParkingLot parkingLotSelection = this.parkingLots.stream().filter(parkingLot -> !parkingLot.isFull()).findFirst().get();
        return parkingLotSelection.park(car);
    }


    public Car fetch(ParkingTicket parkingTicket) {

       return this.parkingLots.get(0).fetch(parkingTicket);
    }

}
