package com.devansh.personal.exception;

public class ParkingLotException
        extends RuntimeException {
    public ParkingLotException(final String message) {
        this(message, null);
    }

    public ParkingLotException(final String message, final Throwable t) {
        super(message, t);
    }
}
