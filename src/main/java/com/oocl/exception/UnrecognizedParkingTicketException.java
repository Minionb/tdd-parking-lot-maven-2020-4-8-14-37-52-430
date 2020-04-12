package com.oocl.exception;

public class UnrecognizedParkingTicketException extends RuntimeException {

    public UnrecognizedParkingTicketException() {
        super("Unrecognized parking ticket.");
    }

}
