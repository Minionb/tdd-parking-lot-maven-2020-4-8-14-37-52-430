package com.oocl.exception;

public class NotEnoughPositionException extends RuntimeException {
    public NotEnoughPositionException(){

        super("Not enough position.");
    }
}
