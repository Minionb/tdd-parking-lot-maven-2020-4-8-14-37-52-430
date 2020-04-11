package com.oocl;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity;
    private Map<ParkingTicket,Car> parkingTicketCarMap = new HashMap<>();

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ParkingLot(int capacity){
        this.capacity = capacity;
    }

    public ParkingLot(){
        setCapacity(10);
    }

    public boolean isFull(){
        return this.getCapacity() <= parkingTicketCarMap.size();
    }
    public ParkingTicket park(Car car){

        if (isFull()){
            return null;
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        this.parkingTicketCarMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket)throws UnrecognizedParkingTicketException, NoTicketException {
        if(parkingTicket == null){
            throw new NoTicketException();
        }

        if (!this.parkingTicketCarMap.containsKey(parkingTicket)){
           throw new UnrecognizedParkingTicketException();
        }

        Car car = this.parkingTicketCarMap.remove(parkingTicket);
        return car;
    }


}
