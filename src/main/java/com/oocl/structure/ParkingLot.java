package com.oocl.structure;

import com.oocl.exception.NoTicketException;
import com.oocl.exception.NotEnoughPositionException;
import com.oocl.exception.UnrecognizedParkingTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity;

    private Map<ParkingTicket, Car> parkingTicketCarMap = new HashMap<>();

    public int getEmptyPosition() {
        return this.capacity - parkingTicketCarMap.size();
    }

    public double getRemainingRatio() {
        return (double) getEmptyPosition() / this.capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingLot() {
        setCapacity(10);
    }

    public boolean isFull() {
        return this.getCapacity() <= parkingTicketCarMap.size();
    }


    public ParkingTicket park(Car car) {
        if (isFull()) {
            throw new NotEnoughPositionException();
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        this.parkingTicketCarMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (parkingTicket == null) {
            throw new NoTicketException();
        }

        if (!this.parkingTicketCarMap.containsKey(parkingTicket)) {
            throw new UnrecognizedParkingTicketException();
        }

        return this.parkingTicketCarMap.remove(parkingTicket);
    }


}
