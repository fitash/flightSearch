package com.flightsearch.data;

import com.flightsearch.data.exceptions.CSVReadingException;
import com.flightsearch.data.exceptions.DataExceptionMessagesConstants;
import com.flightsearch.domain.Airport;
import com.flightsearch.domain.Flight;


public class FlightParser implements Parser<Flight> {
    public Flight parse(String[] line) throws CSVReadingException {
        try {
            Airport parsedOrigin = Airport.valueOf(line[0]);
            Airport parsedDestination = Airport.valueOf(line[1]);
            String flightCode = line[2];
            Double parsedPrice = Double.parseDouble(line[3]);

            return new Flight(parsedOrigin, parsedDestination, flightCode, parsedPrice);
        }catch (Exception e){
            throw new CSVReadingException(DataExceptionMessagesConstants.WRONG_FORMAT,e);
        }
    }
}
