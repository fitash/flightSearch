package com.flightsearch.domain.exceptions;


public class WrongFlightRequestParametersException extends Exception {
    public WrongFlightRequestParametersException(String message) {
        super(message);
    }
}
