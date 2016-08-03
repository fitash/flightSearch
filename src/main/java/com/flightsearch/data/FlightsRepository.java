package com.flightsearch.data;

import com.flightsearch.data.exceptions.CSVReadingException;
import com.flightsearch.data.exceptions.DataExceptionMessagesConstants;
import com.flightsearch.data.exceptions.RepositoryInitializationException;
import com.flightsearch.domain.Flight;

import java.util.List;


public class FlightsRepository {
List<Flight> flights;
public void init() throws RepositoryInitializationException{
    CSVReader csvReader = new CSVReader();
    csvReader.init(new FlightParser());
    String csvFilePath=CSVReader.class.getResource("/flights.csv").getPath();

    try {
        this.flights=csvReader.read(csvFilePath);
    } catch (CSVReadingException e) {
        throw new RepositoryInitializationException(DataExceptionMessagesConstants.REPOSITORY_INITIALIZATION,e);
    }
}

    public List<Flight> getFlights(){
        return this.flights;
    }
}
