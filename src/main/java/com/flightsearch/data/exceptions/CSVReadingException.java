package com.flightsearch.data.exceptions;

/**
 * Created by rgonza on 2/08/16.
 */
public class CSVReadingException extends Exception{
    public CSVReadingException(String message, Exception e) {
        super(message,e);
    }

}
