package com.oocl;

public class NotEnoughPositionException extends RuntimeException {
    public NotEnoughPositionException(){

        super("Not enough position.");
    }
}
