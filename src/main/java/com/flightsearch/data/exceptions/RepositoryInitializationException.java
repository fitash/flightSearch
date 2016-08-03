package com.flightsearch.data.exceptions;

/**
 * Created by rgonza on 3/08/16.
 */
public class RepositoryInitializationException extends Exception{
    public RepositoryInitializationException(String message, Exception e) {
        super(message,e);
    }
}
